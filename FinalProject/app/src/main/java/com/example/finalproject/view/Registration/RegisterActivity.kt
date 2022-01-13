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
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegisterBinding.inflate(layoutInflater)

        //Joined date
        val date = Date()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val today = Date(year,month,day)
        val months = month + 1

        val d = ("${months}/${year}")
        println(d)

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
                            "name" to binding.editTextnameRegister.text.toString(),
                            "date" to date


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