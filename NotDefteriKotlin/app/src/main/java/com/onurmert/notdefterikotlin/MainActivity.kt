package com.onurmert.notdefterikotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.onurmert.notdefterikotlin.View.CurrentPage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent  = Intent(this, CurrentPage::class.java)

        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 800)
    }
}