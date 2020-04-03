package com.example.rssnewsreader


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter constructor(context : Context, newdata : List<NewsData>) : RecyclerView.Adapter<NewsViewHolder>() {
    private var context : Context
    private var newsData : List<NewsData>

    init {
        this.context = context
        this.newsData = newdata

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler_view_datail,parent,false)
        var newsViewHolder = NewsViewHolder(view)
        return newsViewHolder
    }

    override fun getItemCount(): Int = this.newsData.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item : NewsData = newsData.get(position)


        holder.newstitle.setText(item.title)
        holder.newscontext.setText(item.maintext)
        holder.keyword1.setText(item.key1)
        holder.keyword2.setText(item.key2)
        holder.keyword3.setText(item.key3)
        Glide.with(holder.itemView).load(newsData.get(position).img).centerCrop().override(300,400).into(holder.newsimg)
        holder.itemView.setOnClickListener{
            val openurl = Intent(this.context,web_view_activity::class.java)
            openurl.putExtra("URL",newsData.get(position).Url)
            startActivity(context,openurl,null)
        }




    }

}

class NewsViewHolder constructor(itemview :View):RecyclerView.ViewHolder(itemview)
{

    var newsimg = itemview.findViewById<ImageView>(R.id.NewsImage)
    var newstitle = itemview.findViewById<TextView>(R.id.NewsTitle)
    var newscontext = itemview.findViewById<TextView>(R.id.NewsContext)
    var keyword1 = itemview.findViewById<TextView>(R.id.keyword1)
    var keyword2 = itemview.findViewById<TextView>(R.id.keyword2)
    var keyword3 = itemview.findViewById<TextView>(R.id.keyword3)

}
