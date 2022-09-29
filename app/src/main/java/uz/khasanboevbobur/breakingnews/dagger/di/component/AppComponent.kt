package uz.khasanboevbobur.breakingnews.dagger.di.component

import dagger.Component
import uz.khasanboevbobur.breakingnews.dagger.di.modul.DatabaseModule
import uz.khasanboevbobur.breakingnews.dagger.di.modul.NetworkModule
import uz.khasanboevbobur.breakingnews.fragments.*
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(homeFragment: HomeFragment)

    fun injectArticleF(homeFragment: ArticleFragment)

    fun injectCategory(homeFragment: CategoryFragment)

    fun injectBook(homeFragment: BookmarkFragment)

    fun injectBottom(homeFragment: BottomSheetFragment)



}