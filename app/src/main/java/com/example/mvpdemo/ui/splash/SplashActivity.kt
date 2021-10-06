package com.example.mvpdemo.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.R
import com.example.mvpdemo.ui.home.HomePageActivity

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time. This is just for Demo purpose
        Handler().postDelayed({
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}