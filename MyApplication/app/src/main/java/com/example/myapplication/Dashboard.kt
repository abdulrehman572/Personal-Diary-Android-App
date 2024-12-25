package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val createDiaryButton = findViewById<Button>(R.id.create_diary_button)
        val openDiaryButton = findViewById<Button>(R.id.open_diary_button)

        // Show saved notes when the "Open Saved Diary" button is clicked
        openDiaryButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)
            val savedNotes = sharedPreferences.all

            if (savedNotes.isEmpty()) {
                Toast.makeText(this, "No saved notes", Toast.LENGTH_SHORT).show()
            } else {
                val notesText = StringBuilder()
                for ((title, note) in savedNotes) {
                    notesText.append("Title: $title\nNote: $note\n\n")
                }

                // Display the saved notes in a TextView
                val savedNotesTextView = TextView(this)
                savedNotesTextView.text = notesText.toString()
                setContentView(savedNotesTextView) // Show saved notes in a new view

                // Alternatively, you can use a RecyclerView for better UI and performance
            }
        }

        // Navigate to the create note page
        createDiaryButton.setOnClickListener {
            val intent = Intent(applicationContext, Mainpage::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
