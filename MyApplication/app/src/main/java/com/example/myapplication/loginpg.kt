package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class loginpg : AppCompatActivity() {
    var username_l: String? = null
    var password_l: String? = null

    /*override fun onBackPressed() {
        var signup_intent= Intent(applicationContext,MainActivity::class.java)
        startActivity(signup_intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
        finish()
    }*/
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginpg2)
        database = FirebaseDatabase.getInstance().getReference("Users")
        val username: EditText = findViewById<EditText>(R.id.editTextText2)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword3)
        val signup: Button = findViewById<Button>(R.id.button4)
        val login: Button = findViewById<Button>(R.id.button3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signup.setOnClickListener {
            var signup_intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(signup_intent)
            finish()
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
        }
        login.setOnClickListener {
            username_l = username.text.toString()
            password_l = password.text.toString()
            database.child(username_l!!).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val passwordFromDB = snapshot.child("password").value
                    if (password_l == passwordFromDB) {
                        Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT)
                            .show()
                        var intent = Intent(applicationContext, Dashboard::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                    } else {
                        Toast.makeText(applicationContext, "Fill All Fields", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}
