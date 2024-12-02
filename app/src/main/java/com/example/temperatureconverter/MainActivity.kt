package com.example.temperatureconverter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_search -> {
                Toast.makeText(
                    this, "Action pour le bouton de recherche",
                    Toast.LENGTH_SHORT
                )
                    .show()
                true
            }

            R.id.action_about -> {
                val toast = Toast.makeText(
                    this,
                    "Action pour les paramètres",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                true
            }

            R.id.action_settings -> {
                Toast.makeText(
                    this, "Action pour le à propos",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
