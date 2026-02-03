package com.example.experiment_01_a

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var fontSize = 20f
    private var colorIndex = 0
    private var bgColorIndex = 0

    private val textColors = listOf(
        R.color.black,
        R.color.red,
        R.color.green,
        R.color.blue,
        R.color.yellow,
        R.color.slate_gray,
        R.color.royal_amethyst,
        R.color.sage_green,
        R.color.emerald_green,
        R.color.petrol_blue,
        R.color.royal_blue,
        R.color.wine_red,
        R.color.crimson_red

    )

    private val bgColors = listOf(
        android.R.color.white,
        android.R.color.holo_blue_light,
        android.R.color.holo_red_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_purple,
        android.R.color.holo_green_light,
        android.R.color.holo_green_dark
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val main = findViewById<LinearLayout>(R.id.main)
        val textView = findViewById<TextView>(R.id.textViewHello)
        val changeSize = findViewById<Button>(R.id.changeSize)
        val changeColor = findViewById<Button>(R.id.changeColor)
        val changeBgColor = findViewById<Button>(R.id.changeBgColor)

        changeSize.setOnClickListener {
            fontSize = if (fontSize >= 40f) 20f else fontSize + 5
            textView.textSize = fontSize
        }

        changeColor.setOnClickListener {
            colorIndex = (colorIndex + 1) % textColors.size
            textView.setTextColor(ContextCompat.getColor(this, textColors[colorIndex]))
        }

        changeBgColor.setOnClickListener {
            bgColorIndex = (bgColorIndex + 1) % bgColors.size
            main.setBackgroundColor(ContextCompat.getColor(this, bgColors[bgColorIndex]))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}