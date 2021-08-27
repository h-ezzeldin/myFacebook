package com.example.myfacebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myfacebook.R
import com.example.myfacebook.data.PostInterface
import com.example.myfacebook.models.PostModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewPostActivity : AppCompatActivity() {
    private val BASE_URL : String = "https://jsonplaceholder.typicode.com/"
    lateinit var submitButton: MaterialButton
    lateinit var userIdInput: TextInputEditText
    lateinit var titleInput: TextInputEditText
    lateinit var bodyInput: TextInputEditText
    lateinit var resultOutput: TextView
    lateinit var postInterface: PostInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)
        resultOutput = findViewById(R.id.result_output)
        userIdInput = findViewById(R.id.new_user_id_input)
        titleInput = findViewById(R.id.new_title_input)
        bodyInput = findViewById(R.id.new_body_input)
        submitButton = findViewById(R.id.submit_button)

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
       postInterface = retrofit.create(PostInterface::class.java)

        submitButton.setOnClickListener {
            if(!userIdInput.text.isNullOrEmpty() &&
                !titleInput.text.isNullOrEmpty() &&
                !bodyInput.text.isNullOrEmpty())
            {

                addNewPost(
                    PostModel( userIdInput.text.toString().toInt(),
                        titleInput.text.toString(), bodyInput.text.toString()))

            }
        }
    }

    fun addNewPost(postModel: PostModel){
        val observable: Single<PostModel> = postInterface.addPost(postModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        var singleObserver: SingleObserver<PostModel> = object:
            SingleObserver<PostModel> {
            override fun onSubscribe(d: Disposable) {
                //compositeDisposable.add(d)
            }

            override fun onSuccess(t: PostModel) {
                resultOutput.text = t.title
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "onError: ${e.message}")
            }

        }
        observable.subscribe(singleObserver)
    }
}