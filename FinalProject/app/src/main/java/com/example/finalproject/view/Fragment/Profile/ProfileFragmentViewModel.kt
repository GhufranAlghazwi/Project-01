package com.example.finalproject.view.Fragment.Profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.repository.Repository
import com.example.finalproject.model.Profile

class ProfileFragmentViewModel: ViewModel(){

    val user = Repository()

    fun getUserAccount(): LiveData<Profile> {
        return user.getUserData()
    }


    fun updateUserProfile(avatar: String): LiveData<Boolean>{
        return user.updateUserProfile(avatar)
    }

    fun updateUserBio(bio: String): LiveData<Boolean>{
        return user.updateUserBio(bio)
    }








}