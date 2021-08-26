package com.example.myfacebook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfacebook.data.PostInterface
import com.example.myfacebook.models.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {
    var postLD : MutableLiveData<MutableList<PostModel>> = MutableLiveData()
    private val BASE_URL : String = "https://jsonplaceholder.typicode.com/"
    private var postInterface : PostInterface

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        postInterface = retrofit.create(PostInterface::class.java)
    }

    fun getPosts(){

        postInterface.getPosts().enqueue(object :Callback<MutableList<PostModel>>{
            override fun onResponse(
                call: Call<MutableList<PostModel>>,
                response: Response<MutableList<PostModel>>
            ) {
                Log.d("TAG", "onResponse: "+ (response.body()?.get(0)?.id).toString())
                postLD.value = response.body()
            }

            override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }
}