package com.example.counter_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var counterText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        counterText = findViewById(R.id.counter_text)
        val plusButton: Button = findViewById(R.id.plus_button)
        val minusButton: Button = findViewById(R.id.minus_button)
        val resetButton: Button = findViewById(R.id.reset_button)

        updateCounterText()

        plusButton.setOnClickListener {
            counter++
            updateCounterText()
        }

        minusButton.setOnClickListener {
            counter--
            updateCounterText()
        }

        resetButton.setOnClickListener {
            counter = 0
            updateCounterText()
        }
    }

    private fun updateCounterText() {
        counterText.text = counter.toString()
    }
}
