package com.example.myfacebook.data

import com.example.myfacebook.models.PostModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET

interface PostInterface {

    @GET("posts")
    fun getPosts(): Single<MutableList<PostModel>>
}