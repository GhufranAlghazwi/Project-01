package com.example.finalproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.model.Profile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repository {
    var db = Firebase.firestore
    var uid = Firebase.auth.currentUser?.uid

    //get user account from Firebase Firestore
    fun getUserData(): LiveData<Profile> {

        //auth = Firebase.auth
        val mLivedata = MutableLiveData<Profile>()

        db.collection("user").document(uid!!)
            .addSnapshotListener { dr, message ->
                if (dr != null) {
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
    fun uploadimage() {

    }

    //update image profile
    fun updateUserProfile(avatar: String): LiveData<Boolean> {
        var mLiveDate = MutableLiveData<Boolean>()
        db.collection("user").document(uid!!)
            .update("image" , avatar)
            .addOnSuccessListener {
                mLiveDate.postValue(true)
            }.addOnFailureListener {
                mLiveDate.postValue(false)
            }
        return mLiveDate
    }

    //update image profile
    fun updateUserBio(bio: String): LiveData<Boolean> {
        var mLiveDate = MutableLiveData<Boolean>()
        db.collection("user").document(uid!!)
            .update(
                "description" , bio
            ).addOnSuccessListener {
                mLiveDate.postValue(true)
            }.addOnFailureListener {
                mLiveDate.postValue(false)
            }
        return mLiveDate
    }


}