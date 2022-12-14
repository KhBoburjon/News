package uz.khasanboevbobur.breakingnews.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.khasanboevbobur.breakingnews.App
import uz.khasanboevbobur.breakingnews.MainActivity
import uz.khasanboevbobur.breakingnews.R
import uz.khasanboevbobur.breakingnews.dagger.data.database.MyDatabaseHelper
import uz.khasanboevbobur.breakingnews.dagger.data.entity.ArticleEntity
import uz.khasanboevbobur.breakingnews.databinding.FragmentArticleBinding
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ArticleFragment : Fragment(R.layout.fragment_article), CoroutineScope {

    private val binding by viewBinding(FragmentArticleBinding::bind)
    private lateinit var article: ArticleEntity
    @Inject
    lateinit var myDatabaseHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getSerializable("art") as ArticleEntity
        }
        App.appComponent.injectArticleF(this)
        (activity as MainActivity).hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        activity?.window?.statusBarColor = Color.TRANSPARENT


        binding.apply {

            titleTv.text = article.title

            newsDataTv.text = article.description

            if (article.isSave) {
                saveImg.setImageResource(R.drawable.bookmark_selected)
            } else {
                saveImg.setImageResource(R.drawable.ic_bookmark__1)
            }

            saveImg.setOnClickListener {
                launch {
                    if (article.isSave) {
                        saveImg.setImageResource(R.drawable.ic_bookmark__1)
                        myDatabaseHelper.getHelper().deleteArticle(article)
                        article.isSave = false
                    } else {
                        saveImg.setImageResource(R.drawable.bookmark_selected)
                        article.isSave = true
                        myDatabaseHelper.getHelper().addArticle(article)
                    }
                }
            }

            Glide.with(requireActivity()).load(article.urlToImage).into(image)
            Glide.with(requireActivity()).load(article.urlToImage).into(imageOnBg)

            backImg.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).show()
    }

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main
}