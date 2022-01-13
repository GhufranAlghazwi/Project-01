package com.example.finalproject.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.view.Fragment.Profile.ProfileFragment
import com.example.finalproject.view.Fragment.home.HomeFragment
import com.example.finalproject.view.Fragment.newpost.NewPostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bNavBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                        HomeFragment()
                    ).commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                        ProfileFragment()
                    ).commit()
                    true
                }
                R.id.AddPost ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                        NewPostFragment()
                    ).commit()
                    true
                }
                else -> false
            }
        }


        setContentView(binding.root)
    }
}