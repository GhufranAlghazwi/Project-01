package com.example.finalproject.Repository

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*

class Repository{
    var db = FirebaseFirestore.getInstance()
    lateinit var auth: FirebaseAuth

    //get user account from Firebase Firestore
    fun getUserData(): LiveData<Profile> {

        auth = Firebase.auth
        val mLivedata = MutableLiveData<Profile>()

        db.collection("user").document(auth.currentUser?.uid.toString())
            .addSnapshotListener{dr,message ->
                if(dr != null){
                    mLivedata.postValue(
                        Profile(
                            dr.getString("email").toString(),
                            dr.getString("username").toString(),
                            dr.getString("name").toString(),
                            dr.getString("image").toString(),
                            dr.getString("description").toString(),
                            dr.getDate("date").toString()

                        )
                    )
                }
            }
        return mLivedata
    }

    //upload image
    fun uploadimage(){

    }

    //update profile
    fun updateUserProfile(avatar: String, desc: String): LiveData<Boolean>{
        var mLiveDate = MutableLiveData<Boolean>()
        db.collection("user").document(auth.currentUser?.uid.toString())
            .update(
                mapOf(
                    "image" to avatar,
                    "description" to desc
                )
            ).addOnSuccessListener {
                mLiveDate.postValue(true)
            }.addOnFailureListener {
                mLiveDate.postValue(false)
            }
        return mLiveDate
    }


}