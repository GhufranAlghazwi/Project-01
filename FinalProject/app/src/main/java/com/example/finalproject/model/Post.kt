package com.example.finalproject.model

import java.util.*


data class Post(
    var id:String?= null,
    var username: String,
    var name: String,
    var avatar: String?,
    var postBody: String,
    var likes: Double,
    var time: Date?
)