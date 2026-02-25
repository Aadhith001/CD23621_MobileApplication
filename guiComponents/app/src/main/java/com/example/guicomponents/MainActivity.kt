package com.example.guicomponents

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
        R.color.midnight_black,
        R.color.sunset_orange,
        R.color.ocean_blue,
        R.color.forest_green,
        R.color.royal_purple
    )

    private val bgColors = listOf(
        R.color.ivory_white,
        R.color.sky_blue,
        R.color.mint_green,
        R.color.peach_cream,
        R.color.lavender_mist
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val main = findViewById<LinearLayout>(R.id.main)
        val textView = findViewById<TextView>(R.id.textView)
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