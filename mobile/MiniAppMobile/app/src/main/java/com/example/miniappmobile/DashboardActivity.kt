package com.example.miniappmobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize UI components from the layout
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvUserEmail = findViewById<TextView>(R.id.tvUserEmail)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 1. Retrieve the session data from SharedPreferences
        // This acts as your client-side session management
        val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val fullName = sharedPref.getString("full_name", "User") // Matches full_name in ERD [cite: 1, 46]
        val email = sharedPref.getString("email", "No Email")     // Matches email in ERD [cite: 1, 41]

        // 2. Update the UI with retrieved data
        tvWelcome.text = "Welcome, $fullName!"
        tvUserEmail.text = email

        // 3. Handle Logout functionality
        // As specified in the Activity Diagram: Logout -> End [cite: 1, 76, 77]
        btnLogout.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear() // Remove all saved session data
            editor.apply()

            // Redirect user back to the Login screen (MainActivity)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Ensure the user cannot go back to the dashboard
        }
    }
}