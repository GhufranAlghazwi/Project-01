package com.example.finalproject.Repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

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

                            )
                    )
                }
            }
        return mLivedata
    }

    //upload image
    fun uploadimage(){

    }
}