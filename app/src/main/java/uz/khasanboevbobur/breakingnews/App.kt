package uz.khasanboevbobur.breakingnews

import android.app.Application
import uz.khasanboevbobur.breakingnews.dagger.di.component.AppComponent
import uz.khasanboevbobur.breakingnews.dagger.di.component.DaggerAppComponent
import uz.khasanboevbobur.breakingnews.dagger.di.modul.DatabaseModule


class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }
}