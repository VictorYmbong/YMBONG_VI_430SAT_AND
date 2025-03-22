package com.example.multilayoutlogger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivityLog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // Set up edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add logging at different levels
        Log.v(TAG, "Verbose: App started")
        Log.d(TAG, "Debug: Debugging MainActivity")
        Log.i(TAG, "Info: MainActivity Loaded")
        Log.w(TAG, "Warning: Potential issue detected")
        Log.e(TAG, "Error: Example error message")
    }

    // Navigation methods
    fun openLinearActivity(view: View) {
        Log.d(TAG, "Opening SecondActivity with LinearLayout")
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun openRelativeActivity(view: View) {
        Log.d(TAG, "Opening ThirdActivity with RelativeLayout")
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }
}