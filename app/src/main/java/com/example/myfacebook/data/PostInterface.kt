package com.example.myfacebook.data

import com.example.myfacebook.models.PostModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostInterface {

    @GET("posts")
    fun getPosts(@Query("userId") userId: String): Single<MutableList<PostModel>>

    @POST("posts")
    fun addPost(@Body postModel: PostModel): Single<PostModel>

}