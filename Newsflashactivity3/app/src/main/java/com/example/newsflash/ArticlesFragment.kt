package com.example.newsflash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArticlesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        // Sample news data
        val sampleNews = listOf(
            NewsArticle(
                1,
                "Global Tech Summit 2024 Announced",
                "The biggest tech conference of the year will be held virtually, featuring speakers from leading tech companies.",
                "2024-03-20 09:00"
            ),
            NewsArticle(
                2,
                "New Breakthrough in Renewable Energy",
                "Scientists develop more efficient solar panels that could revolutionize clean energy production.",
                "2024-03-20 10:15"
            ),
            NewsArticle(
                3,
                "Space Tourism Takes Off",
                "First commercial space hotel announces opening date, marking new era in space tourism.",
                "2024-03-20 11:30"
            ),
            NewsArticle(
                4,
                "AI Makes Medical Breakthrough",
                "Artificial Intelligence system successfully predicts protein structures, opening new possibilities in drug development.",
                "2024-03-20 12:45"
            ),
            NewsArticle(
                5,
                "Electric Vehicle Sales Soar",
                "Global EV sales reach record numbers as more countries push for sustainable transportation.",
                "2024-03-20 14:00"
            )
        )

        adapter = NewsAdapter(sampleNews) { article ->
            Toast.makeText(context, "Clicked: ${article.title}", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to article detail
        }
        recyclerView.adapter = adapter
    }
} 