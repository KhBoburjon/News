package uz.khasanboevbobur.breakingnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.khasanboevbobur.breakingnews.R
import uz.khasanboevbobur.breakingnews.adapters.RecAdapterCategory1
import uz.khasanboevbobur.breakingnews.databinding.FragmentCategoryBinding
import uz.khasanboevbobur.breakingnews.madels.adap.RVClass
import uz.khasanboevbobur.breakingnews.madels.languages.LocalHelper
import uz.khasanboevbobur.breakingnews.preference.MyShared

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val binding by viewBinding(FragmentCategoryBinding::bind)
    private lateinit var recAdapterCategory: RecAdapterCategory1
    private lateinit var list: ArrayList<RVClass>
    private var myShared = MyShared
    private var gson = Gson()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myShared = MyShared.getInstance(requireContext())
        loadingList()
        val onAttach = LocalHelper().onAttach(requireContext())
        binding.apply {
            txt1.text = onAttach?.getText(R.string.cat_txt1)
            txt.text = onAttach?.getText(R.string.cat_txt2)
            recAdapterCategory = RecAdapterCategory1(list, object : RecAdapterCategory1.OnCardClicked{
                override fun onclick(rvClass: Int, checked: Boolean) {
                    list[rvClass].isChecked = checked
                    recAdapterCategory.notifyItemChanged(rvClass)
                }

            })
            recycle.adapter = recAdapterCategory

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myShared.setList("category", gson.toJson(list))
    }

    private fun loadingList() {
        list = ArrayList()
        if (myShared.getList("category").isNotEmpty()) {
            val type = object : TypeToken<List<RVClass>>() {}.type
            list = ArrayList(gson.fromJson<List<RVClass>>(myShared.getList("category"), type))
        } else {
            list.add(RVClass("\uD83C\uDFC8 Sports", false))
            list.add(RVClass("\uD83C\uDFAE Technology", false))
            list.add(RVClass("\uD83C\uDFA8 Science", false))
            list.add(RVClass("\uD83C\uDF1E Health", false))
            list.add(RVClass("\uD83C\uDF34 Entertainment", false))
            list.add(RVClass("\uD83D\uDCDC Business", false))
            list.add(RVClass("\uD83D\uDC3B General", false))
        }
    }
}