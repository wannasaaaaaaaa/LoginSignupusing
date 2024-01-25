package com.example.loginsignupusing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupusing.databinding.ActivitySignupBinding

class signupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        binding.signupButton.setOnClickListener {
            val signupUsername = binding.signupUsername.text.toString()
            val signupPasswoord = binding.signupPassword.text.toString()
            signupDatabase(signupUsername,signupPasswoord)
        }
        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private  fun  signupDatabase(username: String, password: String){
        val insertedRowId = databaseHelper.inserUser(username, password)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()

        }
    }
}
