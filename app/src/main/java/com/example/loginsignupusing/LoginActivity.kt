package com.example.loginsignupusing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.loginsignupusing.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityLoginBinding
    private  lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val  loginUsername = binding.loginUsername.text.toString()
            val  loginPassword = binding.loginPassword.text.toString()
            loginDatabase(loginUsername,loginPassword)
        }
        binding.signupRedirect.setOnClickListener {
            val intent = Intent(this, signupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun loginDatabase(username: String, password: String){
        val userExists = databaseHelper.readUser(username, password)
        if (userExists){
            Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}