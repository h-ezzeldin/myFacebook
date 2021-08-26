package com.example.myfacebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfacebook.R
import com.example.myfacebook.adapters.PostAdapter
import com.example.myfacebook.models.PostModel

class MainActivity : AppCompatActivity() {
    private lateinit var postViewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)


        Log.d("TAG", "onCreate: $postViewModel")
        postViewModel.getPosts()
        val recyclerView: RecyclerView = findViewById(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val pAdapter = PostAdapter()
        recyclerView.adapter = pAdapter
        postViewModel.postLD.observe(this, { newList -> run {  pAdapter.setList(newList)}
        })
    }
}