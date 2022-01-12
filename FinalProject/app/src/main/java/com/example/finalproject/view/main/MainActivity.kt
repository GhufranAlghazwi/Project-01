package com.example.finalproject.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bNavBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homePage -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.profile -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.addPost ->{

                    true
                }
                else -> false
            }
        }


        setContentView(binding.root)
    }
}