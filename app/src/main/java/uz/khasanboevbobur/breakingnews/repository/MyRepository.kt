package uz.khasanboevbobur.breakingnews.repository

import kotlinx.coroutines.flow.flow
import uz.khasanboevbobur.breakingnews.dagger.data.dao.ArticleDao
import uz.khasanboevbobur.breakingnews.dagger.data.entity.ArticleEntity
import uz.khasanboevbobur.breakingnews.dagger.retro.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService, private val articleDao: ArticleDao) {

    suspend fun getNewsRepos() = flow { emit(apiService.getNewsRetro()) }
    suspend fun getSearchRepos(q: String) = flow { emit(apiService.getSearchRetro(q)) }
    suspend fun getCategoryRepos(cat: String) = flow { emit(apiService.getCategoryRetro(cat)) }

    suspend fun addUser(articleEntity: ArticleEntity) = articleDao.addArticle(articleEntity)
    suspend fun deleteUser(articleEntity: ArticleEntity) = articleDao.deleteArticle(articleEntity)
    suspend fun getList() = flow { emit(articleDao.getAllArticles()) }
    suspend fun getArt(n: String) = articleDao.getArticleByUrl(n)

}