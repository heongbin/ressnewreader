package com.example.rssnewsreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class web_view_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_activity)
        try {
            val openurl = intent.getStringExtra("URL").toString()
            web_view.webViewClient= WebViewClient()
            val myviewwb = findViewById<View>(R.id.web_view) as WebView
            val settings = myviewwb.settings
            settings.javaScriptEnabled = true
            settings.setSupportMultipleWindows(false)

            myviewwb.loadUrl(openurl)

        }
        catch (e: Exception){}
    }
}
