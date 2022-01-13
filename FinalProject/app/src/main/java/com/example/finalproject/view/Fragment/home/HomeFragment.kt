package com.example.finalproject.view.Fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentHomeBinding
import com.example.finalproject.view.main.MainActivity
import com.example.finalproject.view.newPost.NewPostActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        HomeViewModel().getPosts().observe(viewLifecycleOwner,{
            binding.postsRecyclerView.adapter = HomeAdapter(it)
        })


        val addFabBtn = v.findViewById<FloatingActionButton>(R.id.addFab)
        addFabBtn.setOnClickListener {
            startActivity(Intent(this.context, NewPostActivity::class.java))
        }

        return v
    }

}