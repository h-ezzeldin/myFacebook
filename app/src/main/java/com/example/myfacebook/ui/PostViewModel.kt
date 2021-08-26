package com.example.myfacebook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfacebook.data.PostInterface
import com.example.myfacebook.models.PostModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostViewModel : ViewModel() {
    private val TAG = "tag"
    var postLD : MutableLiveData<MutableList<PostModel>> = MutableLiveData()
    val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private val BASE_URL : String = "https://jsonplaceholder.typicode.com/"
    private var postInterface : PostInterface

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        postInterface = retrofit.create(PostInterface::class.java)
    }

    fun getPosts(){

        val observable: Single<MutableList<PostModel>> = postInterface.getPosts()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
        var singleObserver: SingleObserver<MutableList<PostModel>> = object: SingleObserver<MutableList<PostModel>> {
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onSuccess(t: MutableList<PostModel>) {
                postLD.value = t
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.message}")
            }

        }
        observable.subscribe(singleObserver)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}