package com.example.finalproject.view.Fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.model.Post

class HomeAdapter(var data: List<Post>) : RecyclerView.Adapter<HomeAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_row_post, null)
        return HomeAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: HomeAdapterHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class HomeAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
}

