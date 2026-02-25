package com.example.scientificcal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView

    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)

        // Numbers
        setClick(R.id.btn0, "0")
        setClick(R.id.btn1, "1")
        setClick(R.id.btn2, "2")
        setClick(R.id.btn3, "3")
        setClick(R.id.btn4, "4")
        setClick(R.id.btn5, "5")
        setClick(R.id.btn6, "6")
        setClick(R.id.btn7, "7")
        setClick(R.id.btn8, "8")
        setClick(R.id.btn9, "9")

        // Operators
        setClick(R.id.btnPlus, "+")
        setClick(R.id.btnMinus, "-")
        setClick(R.id.btnMul, "*")
        setClick(R.id.btnDiv, "/")
        setClick(R.id.btnDot, ".")

        // Scientific
        setClick(R.id.btnSqrt, "sqrt(")
        setClick(R.id.btnSin, "sin(")
        setClick(R.id.btnCos, "cos(")
        setClick(R.id.btnTan, "tan(")
        setClick(R.id.btnLog, "log(")

        // AC (C)
        findViewById<Button>(R.id.btnAC).setOnClickListener {
            expression = ""
            tvExpression.text = ""
            tvResult.text = "0"
        }

        // Equal
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            try {
                val result = evaluateExpression(expression)
                tvResult.text = result.toString()
            } catch (_: Exception) {
                tvResult.text = "Error"
            }
        }
    }

    private fun setClick(buttonId: Int, value: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            expression += value
            tvExpression.text = expression
        }
    }

    // ================================
    // SIMPLE SCIENTIFIC EVALUATOR
    // ================================
    private fun evaluateExpression(expr: String): Double {
        return ExpressionParser(expr).parse()
    }

    // =========================================
    // Custom Expression Parser (No Libraries)
    // Supports:
    // + - * / ( ) sqrt sin cos tan log
    // =========================================
    class ExpressionParser(private val str: String) {
        private var pos = -1
        private var ch = 0

        fun parse(): Double {
            nextChar()
            val x = parseExpression()
            if (pos < str.length) throw RuntimeException("Unexpected: ${ch.toChar()}")
            return x
        }

        private fun nextChar() {
            pos++
            ch = if (pos < str.length) str[pos].code else -1
        }

        private fun eat(charToEat: Int): Boolean {
            while (ch == ' '.code) nextChar()
            if (ch == charToEat) {
                nextChar()
                return true
            }
            return false
        }

        private fun parseExpression(): Double {
            var x = parseTerm()
            while (true) {
                x = when {
                    eat('+'.code) -> x + parseTerm()
                    eat('-'.code) -> x - parseTerm()
                    else -> return x
                }
            }
        }

        private fun parseTerm(): Double {
            var x = parseFactor()
            while (true) {
                x = when {
                    eat('*'.code) -> x * parseFactor()
                    eat('/'.code) -> x / parseFactor()
                    else -> return x
                }
            }
        }

        private fun parseFactor(): Double {
            if (eat('+'.code)) return parseFactor()
            if (eat('-'.code)) return -parseFactor()

            var x: Double
            val startPos = pos

            when {
                eat('('.code) -> {
                    x = parseExpression()
                    eat(')'.code)
                }

                ch in '0'.code..'9'.code || ch == '.'.code -> {
                    while (ch in '0'.code..'9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                }

                ch in 'a'.code..'z'.code -> {
                    while (ch in 'a'.code..'z'.code) nextChar()
                    val func = str.substring(startPos, pos)

                    x = parseFactor()

                    x = when (func) {
                        "sqrt" -> sqrt(x)
                        "sin" -> sin(Math.toRadians(x))
                        "cos" -> cos(Math.toRadians(x))
                        "tan" -> tan(Math.toRadians(x))
                        "log" -> log10(x)
                        else -> throw RuntimeException("Unknown function: $func")
                    }
                }

                else -> throw RuntimeException("Unexpected: ${ch.toChar()}")
            }

            return x
        }
    }
}
