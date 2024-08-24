package com.example.todoapp
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.android.ads.mediationtestsuite.activities.HomeActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)


    }
}