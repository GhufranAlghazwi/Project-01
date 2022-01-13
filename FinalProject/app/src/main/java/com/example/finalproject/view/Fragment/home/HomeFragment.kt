package com.example.finalproject.view.Fragment.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentHomeBinding
import com.example.finalproject.view.main.MainActivity
import com.example.finalproject.view.newPost.NewPostActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.content.ContextCompat.getSystemService




class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        var mRecyclerView = v.findViewById<RecyclerView>(R.id.postsRecyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(this.context, 1)
        HomeViewModel().getPosts().observe(viewLifecycleOwner,{
            mRecyclerView.adapter = HomeAdapter(it)
        })


        val addFabBtn = v.findViewById<FloatingActionButton>(R.id.addFab)
        addFabBtn.setOnClickListener {
            startActivity(Intent(this.context, NewPostActivity::class.java))
        }

        return v
    }

}