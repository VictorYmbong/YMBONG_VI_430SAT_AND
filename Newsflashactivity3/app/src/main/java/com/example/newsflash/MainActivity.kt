package com.example.newsflash

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        
        // Set initial fragment
        if (savedInstanceState == null) {
            loadFragment(ArticlesFragment())
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_articles -> {
                    loadFragment(ArticlesFragment())
                    true
                }
                R.id.navigation_favorites -> {
                    loadFragment(FavoritesFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}