package com.example.adminshriparvatioilandproduct

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var adminRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        loginButton = findViewById(R.id.Loginbutton)
        progressBar = findViewById(R.id.progressBar) // Ensure a ProgressBar is in the XML

        adminRef = FirebaseDatabase.getInstance().getReference("admin")

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "Please enter both username and password.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        loginButton.isEnabled = false
        progressBar.visibility = View.VISIBLE

        adminRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                loginButton.isEnabled = true
                progressBar.visibility = View.GONE

                if (snapshot.exists()) {
                    // Fetch email and password from the 'admin' node
                    val storedEmail = snapshot.child("email").getValue(String::class.java) ?: ""
                    val storedPassword = snapshot.child("password").getValue(String::class.java) ?: ""

                    Log.d("LoginActivity", "Stored Email: $storedEmail")
                    Log.d("LoginActivity", "Stored Password: $storedPassword")

                    if (email == storedEmail && password == storedPassword) {
                        Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                        // Navigate to the next activity
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Invalid email or password.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Admin credentials not found in the database.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                loginButton.isEnabled = true
                progressBar.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
