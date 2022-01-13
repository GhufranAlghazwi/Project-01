package com.example.finalproject.model

import com.google.firebase.firestore.DocumentId
import java.util.*


data class Post(
    @DocumentId
    var id:String?= "",
    var username: String,
    var name: String,
    var avatar: String?,
    var postBody: String,
    var likes: Double,
    var time: Date?,

)