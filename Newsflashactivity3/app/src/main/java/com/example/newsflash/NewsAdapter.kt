package com.example.newsflash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    private val articles: List<NewsArticle>,
    private val onItemClick: (NewsArticle) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val summaryTextView: TextView = view.findViewById(R.id.summaryTextView)
        val timestampTextView: TextView = view.findViewById(R.id.timestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_article, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.titleTextView.text = article.title
        holder.summaryTextView.text = article.summary
        holder.timestampTextView.text = article.timestamp
        holder.itemView.setOnClickListener { onItemClick(article) }
    }

    override fun getItemCount() = articles.size
} 