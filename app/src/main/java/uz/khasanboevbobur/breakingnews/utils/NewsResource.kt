package uz.khasanboevbobur.breakingnews.utils

import uz.khasanboevbobur.breakingnews.dagger.data.entity.ArticleEntity

sealed class NewsResource {

    object Loading: NewsResource()

    class Success(val list: List<ArticleEntity>): NewsResource()

    class Error(val str: String): NewsResource()
}