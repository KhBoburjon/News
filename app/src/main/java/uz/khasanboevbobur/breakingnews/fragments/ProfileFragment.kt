package uz.khasanboevbobur.breakingnews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.khasanboevbobur.breakingnews.R
import uz.khasanboevbobur.breakingnews.databinding.FragmentProfileBinding
import uz.khasanboevbobur.breakingnews.madels.languages.LocalHelper
import uz.khasanboevbobur.breakingnews.preference.MyShared

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    var myShared = MyShared

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myShared = MyShared.getInstance(requireContext())

        val onAttach = LocalHelper().onAttach(requireContext())
        binding.apply {

            title.text = onAttach?.getText(R.string.setting_txt1)
            txtLight.text = onAttach?.getText(R.string.setting_txt2)
            txtDark.text = onAttach?.getText(R.string.setting_txt3)
            val boolean = myShared.getList("theme")
            sw.isChecked = (boolean == "" || boolean == "0")

            sw.setOnCheckedChangeListener { view, isChecked ->
                if (!isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    myShared.setList("theme", "1")
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    myShared.setList("theme", "0")
                }
            }

            lanCard.setOnClickListener {
                findNavController().navigate(R.id.languageFragment)
            }

        }
    }
}