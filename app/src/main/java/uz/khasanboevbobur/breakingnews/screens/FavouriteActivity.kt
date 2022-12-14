package uz.khasanboevbobur.breakingnews.screens

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import uz.khasanboevbobur.breakingnews.MainActivity
import uz.khasanboevbobur.breakingnews.R
import uz.khasanboevbobur.breakingnews.adapters.RecAdapterCategory
import uz.khasanboevbobur.breakingnews.databinding.ActivityFavouriteBinding
import uz.khasanboevbobur.breakingnews.madels.adap.RVClass
import uz.khasanboevbobur.breakingnews.madels.languages.LocalHelper
import uz.khasanboevbobur.breakingnews.preference.MyShared

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var recAdapterCategory: RecAdapterCategory
    private lateinit var list: ArrayList<RVClass>
    private var myShared = MyShared
    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window?.statusBarColor = Color.WHITE
        val onAttach = LocalHelper().onAttach(this)
        myShared = MyShared.getInstance(this)

        loadingList()
        binding.apply {
            txt1.text = onAttach?.getText(R.string.favourite_txt1)
            txt2.text = onAttach?.getText(R.string.favourite_txt2)
            recAdapterCategory = RecAdapterCategory(list, object : RecAdapterCategory.OnCardClicked{
                override fun onclick(rvClass: Int, checked: Boolean) {
                    list[rvClass].isChecked = checked
                    recAdapterCategory.notifyItemChanged(rvClass)
                }

            })
            recycle.adapter = recAdapterCategory

            cardNext.setOnClickListener {
                startActivity(Intent(this@FavouriteActivity, MainActivity::class.java))
                myShared.setList("category", gson.toJson(list))
                finish()
            }

        }
    }

    private fun loadingList() {
        list = ArrayList()
        list.add(RVClass("\uD83C\uDFC8 Sports", false))
        list.add(RVClass("\uD83C\uDFAE Technology", false))
        list.add(RVClass("\uD83C\uDFA8 Science", false))
        list.add(RVClass("\uD83C\uDF1E Health", false))
        list.add(RVClass("\uD83C\uDF34 Entertainment", false))
        list.add(RVClass("\uD83D\uDCDC Business", false))
        list.add(RVClass("\uD83D\uDC3B General", false))
    }
}