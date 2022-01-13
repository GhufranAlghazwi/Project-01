package com.example.finalproject.view.Fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.model.Post

class HomeAdapter(var data: List<Post>) : RecyclerView.Adapter<HomeAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_row_post, null)
        return HomeAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: HomeAdapterHolder, position: Int) {
        holder.name.text = data[position].name
        holder.username.text = "@"+data[position].username
        holder.post.text = data[position].postBody
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class HomeAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var name = v.findViewById<TextView>(R.id.nameTextView)
    var username = v.findViewById<TextView>(R.id.userNameTextView)
    var avatar = v.findViewById<ImageView>(R.id.profile_image)
    var post = v.findViewById<TextView>(R.id.postBodyTextView)
}

