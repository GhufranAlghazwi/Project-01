package com.example.finalproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.finalproject.model.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostRepository {
    val db = Firebase.firestore

    fun getPosts(): MutableLiveData<List<Post>>{
        var mLiveData = MutableLiveData<List<Post>>()

        db.collection("posts").addSnapshotListener { result, error ->
            var postList = mutableListOf<Post>()
            if (result != null){
                postList.clear()
                for (document in result){
                    postList.add(
                        Post(
                            document.id,
                            document.getString("username")!!,
                            document.getString("name")!!,
                            document.getString("avatar"),
                            document.getString("postBody")!!,
                            document.getDouble("likes")!!,
                            document.getDate("postTime"),

                        )
                    )
                }
            }
            mLiveData.postValue(postList)
        }
        return mLiveData
    }
}