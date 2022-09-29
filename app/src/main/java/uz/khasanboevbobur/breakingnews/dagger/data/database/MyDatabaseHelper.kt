package uz.khasanboevbobur.breakingnews.dagger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.khasanboevbobur.breakingnews.dagger.data.dao.ArticleDao
import uz.khasanboevbobur.breakingnews.dagger.data.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class MyDatabaseHelper: RoomDatabase() {

    abstract fun getHelper(): ArticleDao

}