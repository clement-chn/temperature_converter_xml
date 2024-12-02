package com.example.temperatureconverter

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children

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
                displayDialog(savedInstanceState = null)
                true
            }

            R.id.action_about -> {
                /*val toast = Toast.makeText(
                    this,
                    "Action pour les paramètres",
                    Toast.LENGTH_SHORT
                )
                toast.show()*/
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
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

     private fun displayDialog (savedInstanceState: Bundle?): Dialog {

         val radioGroup = RadioGroup(this)
         val radioButton1 = RadioButton(this)
         val radioButton2 = RadioButton(this)

         radioButton1.text = "option1"
         radioButton2.text = "option2"

         radioGroup.addView(radioButton1)
         radioGroup.addView(radioButton2)

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Titre de la boite de dialogue")
            .setMessage("Ceci est un message de boite de dialogue")
            .setView(radioGroup)
            .setPositiveButton("OK") {
                dialog, _->
                dialog.dismiss()
                val radioButtons = radioGroup.children.filterIsInstance<RadioButton>()
                val checkedRadioButton = radioButtons.firstOrNull(){ it.isChecked }

                Toast.makeText(this, checkedRadioButton?.text, Toast.LENGTH_SHORT)
                    .show()
            }
            .create()

        alertDialog.show()
         return alertDialog
    }

}
