package com.example.finalproject.view.Login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityLoginBinding
import com.example.finalproject.view.Registration.RegisterActivity
import com.example.finalproject.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        binding.buttonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.editTextusernameLogin.text.toString(),binding.editTextpasswordLogin.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        Toast.makeText(this,"Login Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Login Faield", Toast.LENGTH_SHORT).show()

                    }
                }.addOnFailureListener {
                    Log.d(ContentValues.TAG,"onCreate: ${it.message}")
                }
        }
        binding.textViewSignUp.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

    }
}