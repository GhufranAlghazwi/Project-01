package com.example.finalproject.view.Registration

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityRegisterBinding
import com.example.finalproject.view.Login.LoginActivity
import com.example.finalproject.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegisterBinding.inflate(layoutInflater)

        auth = Firebase.auth
        val db = Firebase.firestore
        binding.buttonSignUp.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.editTextEmailRegister.text.toString(),
                binding.editTextpasswordRegister.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val user = hashMapOf(
                            "username" to binding.editTextusernameRegister.text.toString(),
                            "email" to auth.currentUser?.email,
                        )
                        db.collection("user").document(auth.currentUser?.uid.toString())
                            .set(user)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this, "Unable to sign-up", Toast.LENGTH_SHORT).show()
                    }

                }.addOnFailureListener { e ->
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    Log.d(ContentValues.TAG, "")
                }
        }

        binding.textViewSignIn.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        setContentView(binding.root)

    }
}