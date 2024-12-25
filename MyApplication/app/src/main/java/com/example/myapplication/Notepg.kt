package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Notepg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notepg)
        
        val editTextTitle = findViewById<EditText>(R.id.editTextText3)
        val editTextNote = findViewById<EditText>(R.id.editTextTextMultiLine)
        val buttonSave = findViewById<Button>(R.id.button5)
        val backButton = findViewById<ImageButton>(R.id.imageButton)

        // Save note on button click
        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val note = editTextNote.text.toString()

            if (title.isNotBlank() && note.isNotBlank()) {
                // Save the note in SharedPreferences
                val sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(title, note)  // Save note with title as key
                editor.apply()

                // Navigate back to Dashboard
                val intent = Intent(applicationContext, Dashboard::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                finish()
            }
        }

        // Navigate back to the dashboard on back button click
        backButton.setOnClickListener {
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
