package com.example.finalproject.view.newPost

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import com.example.finalproject.databinding.ActivityNewPostBinding
import com.example.finalproject.view.Fragment.Profile.ProfileFragmentViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class NewPostActivity : AppCompatActivity() {
    val db = Firebase.firestore
    val vm: ProfileFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)

        val imgr: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        binding.postButton.isEnabled = !binding.postEditText.text.isEmpty()
        binding.postEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.postButton.isEnabled = !binding.postEditText.text.isEmpty()
            }
        })


        var name = ""
        var username = ""
        var avatar = ""
        vm.getUserAccount().observe(this, {
            name = it.Name!!
            username = it.Username!!
            avatar = it.avatar!!

        })

        binding.postButton.setOnClickListener {
            val date = Date()
            val post = hashMapOf(
                "username" to username,
                "name" to name,
                "avatar" to avatar,
                "postBody" to binding.postEditText.text.toString(),
                "likes" to 0,
                "postTime" to date
            )
            //var post = Post(username,name, null, binding.postEditText.text.toString(), 0, null)
            db.collection("posts")
                .add(post)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "post added", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "something went wrong! ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        binding.closeBtn.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }
}