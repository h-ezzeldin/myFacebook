package com.example.myfacebook.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfacebook.R
import com.example.myfacebook.adapters.PostAdapter
import com.example.myfacebook.models.PostModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var postViewModel: PostViewModel
    lateinit var searchButton: MaterialButton
    lateinit var userIdInput: TextInputEditText
    lateinit var newPostActionFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        userIdInput = findViewById(R.id.user_id_input)
        searchButton = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            if (userIdInput.text != null) {
                postViewModel.getPosts(userIdInput.text.toString())
            }
        }
        newPostActionFAB = findViewById(R.id.new_post_fab)
        newPostActionFAB.setOnClickListener {
            startActivity( Intent(this, NewPostActivity::class.java))
        }

        Log.d("TAG", "onCreate: $postViewModel")
        //postViewModel.getPosts()
        val recyclerView: RecyclerView = findViewById(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val pAdapter = PostAdapter()
        recyclerView.adapter = pAdapter
        postViewModel.postLD.observe(this, { newList -> run {  pAdapter.setList(newList.asReversed())}
        })
    }
}