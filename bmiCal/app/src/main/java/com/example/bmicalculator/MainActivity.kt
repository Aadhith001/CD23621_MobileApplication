package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val heightEditText = findViewById<EditText>(R.id.heightEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val weightStr = weightEditText.text.toString()
            val heightStr = heightEditText.text.toString()

            if (weightStr.isEmpty() || heightStr.isEmpty()) {
                resultTextView.text = "Please enter weight and height"
                resultTextView.setTextColor(getColor(R.color.error_red))
                return@setOnClickListener
            }

            val weight = weightStr.toFloat()
            val height = heightStr.toFloat()

            if (height <= 0) {
                resultTextView.text = "Height must be greater than 0"
                resultTextView.setTextColor(getColor(R.color.error_red))
                return@setOnClickListener
            }

            val bmi = weight / (height * height)

            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Normal"
                bmi < 30 -> "Overweight"
                else -> "Obese"
            }

            resultTextView.setTextColor(getColor(R.color.accent_green))
            resultTextView.text = "BMI: %.2f\nCategory: %s".format(bmi, category)
        }
    }
}
