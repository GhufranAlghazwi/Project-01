package com.example.finalproject.view.Fragment.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Repository.Repository
import com.example.finalproject.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfileFragmentViewModel: ViewModel(){

    val user = Repository()

    fun getUserAccount(): LiveData<Profile> {
        return user.getUserData()
    }



}