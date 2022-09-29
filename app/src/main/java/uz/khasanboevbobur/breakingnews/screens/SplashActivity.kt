package uz.khasanboevbobur.breakingnews.screens

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import uz.khasanboevbobur.breakingnews.MainActivity
import uz.khasanboevbobur.breakingnews.databinding.ActivitySplashBinding
import uz.khasanboevbobur.breakingnews.preference.MyShared

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler
    private var myShared = MyShared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        myShared = MyShared.getInstance(this)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val boolean = myShared.getList("theme")
        if (boolean == "1") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else if (boolean == "0"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (myShared.getList("category").isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, OnboardActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 500)
    }
}