package com.example.temperatureconverter

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tempNumberEditText = findViewById<EditText>(R.id.tempNumber)
        val tempBtn = findViewById<Button>(R.id.tempBtn)
        val tempOutput = findViewById<TextView>(R.id.temp)
        val tempImage = findViewById<ImageView>(R.id.hot)

        tempBtn.setOnClickListener {
            val tempInput = tempNumberEditText.text.toString()

            if (tempInput.isNotEmpty()) {
                val temperatureCelsius = tempInput.toDouble()

                val temperatureFahrenheit = (temperatureCelsius * 9/5) + 32
                tempOutput.text = "$temperatureFahrenheit °F"

                if (temperatureCelsius > 14) {
                    tempImage.setImageResource(R.drawable.hot)
                } else {
                    tempImage.setImageResource(R.drawable.cold)
                }
            }
        }
    }
}
