package com.example.rssnewsreader

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.renderscript.Element
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_datail.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class splash : AppCompatActivity() {
    val weburl = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        swipfresh.setOnRefreshListener {
            MyAsyncTask().execute(weburl)


            swipfresh.isRefreshing=false
        }
        MyAsyncTask().execute(weburl)


    }



    inner class MyAsyncTask : AsyncTask<String,String,String>(){
        private var result :String = ""
        private var news_data = ArrayList<NewsData>()



        override fun onPreExecute() {
            super.onPreExecute()
            progress_bar.visibility= View.VISIBLE
        }

        override fun doInBackground(vararg params: String?): String {
            val doc : Document = Jsoup.connect("$weburl").get()
            val elts : Elements = doc.select("item")

    elts.forEachIndexed { index, element ->
        try {
        val a_href = element.select("link").text()
        val title = element.select("title").text()

        val docdetail: Document = Jsoup.connect("$a_href").get()
        val urlsc: Elements = docdetail.select("div[itemprop=articleBody]")
        val img = urlsc.select("img").attr("src")
        val main = urlsc.text()

            val map1:MutableMap<String,Int> = mutableMapOf()
            val tmp=main.split(" ")
            for (i in tmp){
                if (i.length<=2){
                    continue

                }
                if (map1[i]==null) {
                    map1[i]=1
                }
                else{
                    map1[i]?.plus(1)
                }

            }

        val sortedbyvalue=map1.toSortedMap(compareByDescending{it}).keys.toList()


        news_data.add(NewsData(a_href,img, title, main,sortedbyvalue[0],sortedbyvalue[1],sortedbyvalue[2]))
    }

        catch (e:Exception){

        }
}

            return doc.title()
    }

        override fun onPostExecute(result: String?) {
            progress_bar.visibility=View.GONE


            news_recycler_view.layoutManager = LinearLayoutManager(this@splash)
            news_recycler_view.adapter = NewsAdapter(this@splash,news_data)
        }
        }






    }






