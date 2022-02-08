package com.main.umtylacademyuser.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.main.umtylacademyuser.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        imageView2.animation = animation

        Handler().postDelayed({
            startActivity(
                Intent(
                    this@SplashActivity,
                    StartActivity::class.java
                )
            )
        }, 1500)
    }
}