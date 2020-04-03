package com.example.rssnewsreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility=(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        var handler = Handler()
        handler.postDelayed({splashhandler()},1000)


    }
    private fun splashhandler() {
        val nextint = Intent(this,splash::class.java)
        startActivity(nextint)
    }


}
