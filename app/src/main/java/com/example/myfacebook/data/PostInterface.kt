package com.example.myfacebook.data

import com.example.myfacebook.models.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface PostInterface {

    @GET("posts")
    fun getPosts(): Call<MutableList<PostModel>>
}