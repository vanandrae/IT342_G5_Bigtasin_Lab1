package com.example.miniappmobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.miniappmobile.model.User
import com.example.miniappmobile.network.RetrofitClient
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val fullName = findViewById<EditText>(R.id.etRegFullName).text.toString()
            val email = findViewById<EditText>(R.id.etRegEmail).text.toString()
            val password = findViewById<EditText>(R.id.etRegPassword).text.toString()

            // Map UI input to User model. Red error is now gone.
            val newUser = User(
                fullName = fullName,
                email = email,
                password = password,
                username = email
            )

            lifecycleScope.launch {
                try {
                    // Call AuthService through RetrofitClient
                    val response = RetrofitClient.instance.register(newUser)
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Registration Successful!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@RegisterActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}