package uz.khasanboevbobur.breakingnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.khasanboevbobur.breakingnews.dagger.data.entity.ArticleEntity
import uz.khasanboevbobur.breakingnews.network.NetworkHelper
import uz.khasanboevbobur.breakingnews.repository.MyRepository
import uz.khasanboevbobur.breakingnews.utils.NewsResource
import javax.inject.Inject

class MyViewModel @Inject constructor(
    val repository: MyRepository,
    val networkHelper: NetworkHelper
) : ViewModel() {

    fun fetchNews(): StateFlow<NewsResource> {
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)

        viewModelScope.launch {
            if (networkHelper.isNetworkConnect()) {
                repository.getNewsRepos()
                    .catch { stateFlow.emit(NewsResource.Error("${it.message}")) }
                    .collect {
                        if (it.isSuccessful) {
                            val body = it.body()?.articles
                            var list = ArrayList<ArticleEntity>()
                            body?.forEachIndexed { index, article ->
                                var articleEntity: ArticleEntity? = null
                                articleEntity = repository.getArt(article.url?:"")
                                var isH = false
                                isH = (articleEntity != null)
                                list.add(
                                    ArticleEntity(
                                        url = article.url,
                                        urlToImage = article.urlToImage,
                                        author = article.author,
                                        content = article.content,
                                        description = article.description,
                                        publishedAt = article.publishedAt,
                                        title = article.title,
                                        isSave = isH
                                    )
                                )
                            }
                            stateFlow.emit(
                                NewsResource.Success(list)
                            )
                        } else {
                            stateFlow.emit(NewsResource.Error("${it.message()}"))
                        }
                    }

            } else {
                repository.getList()
                    .catch {
                        stateFlow.emit(NewsResource.Error("Error with network"))
                    }
                    .collect {
                        stateFlow.emit(
                            NewsResource.Success(it)
                        )
                    }
            }
        }

        return stateFlow
    }


    fun fetchCategory(q: String): StateFlow<NewsResource> {
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)

        viewModelScope.launch {
            if (networkHelper.isNetworkConnect()) {
                repository.getCategoryRepos(q)
                    .catch { stateFlow.emit(NewsResource.Error("${it.message}")) }
                    .collect {
                        if (it.isSuccessful) {
                            val body = it.body()?.articles
                            var list = ArrayList<ArticleEntity>()
                            body?.forEachIndexed { index, article ->
                                var articleEntity: ArticleEntity? = null
                                articleEntity = repository.getArt(article.url?:"")
                                var isH = false
                                isH = (articleEntity != null)
                                list.add(
                                    ArticleEntity(
                                        url = article.url,
                                        urlToImage = article.urlToImage,
                                        author = article.author,
                                        content = article.content,
                                        description = article.description,
                                        publishedAt = article.publishedAt,
                                        title = article.title,
                                        isSave = isH
                                    )
                                )
                            }
                            stateFlow.emit(
                                NewsResource.Success(list)
                            )
                        } else {
                            stateFlow.emit(NewsResource.Error("${it.message()}"))
                        }
                    }


            } else {
                repository.getList()
                    .catch {
                        stateFlow.emit(NewsResource.Error("Error with network"))
                    }
                    .collect {
                        stateFlow.emit(
                            NewsResource.Success(it)
                        )
                    }
            }
        }

        return stateFlow
    }

    //
    fun fetchSearch(q: String): StateFlow<NewsResource> {
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)

        viewModelScope.launch {

            if (networkHelper.isNetworkConnect()) {
                repository.getSearchRepos(q)
                    .catch { stateFlow.emit(NewsResource.Error("${it.message}")) }
                    .collect {
                        if (it.isSuccessful) {
                            val body = it.body()?.articles
                            var list = ArrayList<ArticleEntity>()
                            body?.forEachIndexed { index, article ->
                                var articleEntity: ArticleEntity? = null
                                articleEntity = repository.getArt(article.url?:"")
                                var isH = false
                                isH = (articleEntity != null)
                                list.add(
                                    ArticleEntity(
                                        url = article.url,
                                        urlToImage = article.urlToImage,
                                        author = article.author,
                                        content = article.content,
                                        description = article.description,
                                        publishedAt = article.publishedAt,
                                        title = article.title,
                                        isSave = isH
                                    )
                                )
                            }
                            stateFlow.emit(
                                NewsResource.Success(list)
                            )
                        } else {
                            stateFlow.emit(NewsResource.Error("${it.message()}"))
                        }
                    }

            } else {
                repository.getList()
                    .catch {
                        stateFlow.emit(NewsResource.Error("Error with network"))
                    }
                    .collect {
                        stateFlow.emit(
                            NewsResource.Success(it)
                        )
                    }
            }
        }

        return stateFlow
    }

}