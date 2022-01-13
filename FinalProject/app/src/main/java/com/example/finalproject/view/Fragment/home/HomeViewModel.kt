package com.example.finalproject.view.Fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Repository.PostRepository
import com.example.finalproject.model.Post

class HomeViewModel: ViewModel() {

    fun getPosts():MutableLiveData<List<Post>>{
        return PostRepository().getPosts()
    }
}