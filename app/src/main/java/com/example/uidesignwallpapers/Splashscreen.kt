package com.example.uidesignwallpapers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        Handler().postDelayed(Runnable {
            startActivity(Intent(this,MainActivity::class.java) )
            finish()
        },2500)
    }
}