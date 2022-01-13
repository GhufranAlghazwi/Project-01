package com.example.finalproject.view.Fragment.home

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.model.Post
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var authintiaction= Firebase.auth
var postid: String = ""

class HomeAdapter(var data: List<Post>) : RecyclerView.Adapter<HomeAdapterHolder>() {
    var db = Firebase.firestore
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.list_row_post, null)
        return HomeAdapterHolder(v)

    }

    override fun onBindViewHolder(holder: HomeAdapterHolder, position: Int) {
        holder.name.text = data[position].name
        holder.username.text = "@"+data[position].username
        holder.post.text = data[position].postBody
        holder.likeNo.text = data[position].likes.toInt().toString()

        val NumberOfLikes = data[position].likes

        //Add 1 to likes
        holder.likesImageView.setOnClickListener {

            val add1ToLike = NumberOfLikes?.plus(1)
            //val add1 = 1
            postid = data[position].id.toString()
            Log.d(ContentValues.TAG, "onBindViewHolder: ${add1ToLike}")

            db.collection("posts").document(postid)
               .update(mapOf(
                    "likes" to add1ToLike
                ))
        }
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
    var likesImageView = v.findViewById<ImageView>(R.id.imageViewlike)
    var likeNo = v.findViewById<TextView>(R.id.textViewLikeNo)

}

