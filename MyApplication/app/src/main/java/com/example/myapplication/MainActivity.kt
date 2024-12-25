package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    var username_r:String?=null
    var password_r:String?=null
    var repassword_r:String?=null
    var email_r:String?=null
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") {_, _ ->
                finishAffinity() // Close all activities and exit the app
            }
            .setNegativeButton("No", null)
            .show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_in)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance().getReference("Users")
        val username :EditText=findViewById<EditText>(R.id.editTextText)
        val email:EditText=findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password:EditText=findViewById<EditText>(R.id.editTextTextPassword)
        val repassword:EditText=findViewById<EditText>(R.id.editTextTextPassword2)
        val signup:Button=findViewById<Button>(R.id.button)
        val login:Button=findViewById<Button>(R.id.button2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signup.setOnClickListener{
            username_r=username.text.toString()
            email_r=email.text.toString()
            password_r=password.text.toString()
            repassword_r=repassword.text.toString()
            if (username_r != "" && password_r != "" && repassword_r != "" && email_r != ""){
                if (password_r==repassword_r) {
                    val user = User(username_r, email_r, password_r)
                    database.child(username_r!!).setValue(user).addOnSuccessListener {
                        Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT).show()
                        var Implicit_intent = Intent(applicationContext, loginpg::class.java)
                        startActivity(Implicit_intent)
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                    }
                }
                else{
                    Toast.makeText(applicationContext, "Password is not same", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
        login.setOnClickListener{
            var Implicit_intent=Intent(applicationContext,loginpg::class.java)
            startActivity(Implicit_intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }

    }
}