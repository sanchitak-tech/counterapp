package com.example.counterapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterText = findViewById<TextView>(R.id.counterText)
        val btnIncrement = findViewById<Button>(R.id.btnIncrement)
        val btnDecrement = findViewById<Button>(R.id.btnDecrement)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val userInput = findViewById<EditText>(R.id.userInput)

        fun updateUI() {
            counterText.text = count.toString()
        }

        // ← This is the new part
        userInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                count = if (input.isNotEmpty()) input.toIntOrNull() ?: 0 else 0
                updateUI()
            }
        })

        btnIncrement.setOnClickListener {
            count++
            updateUI()
        }

        btnDecrement.setOnClickListener {
            count--
            updateUI()
        }

        btnReset.setOnClickListener {
            count = 0
            userInput.setText("")
            updateUI()
        }

        updateUI()
    }
}