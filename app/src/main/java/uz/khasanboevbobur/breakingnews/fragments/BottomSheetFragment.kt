package uz.khasanboevbobur.breakingnews.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.khasanboevbobur.breakingnews.App
import uz.khasanboevbobur.breakingnews.R
import uz.khasanboevbobur.breakingnews.adapters.RecAdapterNews
import uz.khasanboevbobur.breakingnews.dagger.data.entity.ArticleEntity
import uz.khasanboevbobur.breakingnews.databinding.FragmentBottomSheetBinding
import uz.khasanboevbobur.breakingnews.utils.NewsResource
import uz.khasanboevbobur.breakingnews.viewmodels.MyViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BottomSheetFragment : SuperBottomSheetFragment(), CoroutineScope {
    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var recAdapterNews: RecAdapterNews
    private var q = ""
    @Inject
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            q = it.getString("id") as String
        }

        App.appComponent.injectBottom(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentBottomSheetBinding.inflate(inflater, container,  false)

        binding.apply {
            recAdapterNews = RecAdapterNews(requireContext(), object : RecAdapterNews.OnCardClicked{
                override fun onclick(articleEntity: ArticleEntity) {
                    val bundle = Bundle()
                    bundle.putSerializable("art", articleEntity)
                    findNavController().navigate(R.id.articleFragment, bundle)
                }

            })
            recycle.adapter = recAdapterNews

            launch {
                myViewModel.fetchSearch(q).collect {
                    when(it) {

                        is NewsResource.Loading -> {

                        }
                        is NewsResource.Success -> {
                            monkeyAnim.visibility = View.GONE
                            recAdapterNews.submitList(it.list)
                        }
                        is NewsResource.Error -> {

                        }

                    }
                }
            }

        }


        return binding.root
    }

    override fun animateStatusBar(): Boolean {
        return false
    }

    override fun getCornerRadius(): Float {
        return 100f
    }

    override fun getDim(): Float {
        return 0.5f
    }

    override fun animateCornerRadius(): Boolean {
        return false
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    @SuppressLint("Range")
    override fun getExpandedHeight(): Int {
        return 2100
    }


    companion object {

        @JvmStatic
        fun newInstance(id: String) =
            BottomSheetFragment().apply {
                arguments = Bundle().apply {
                    this.putString("id", id)
                }
            }
    }

    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main
}