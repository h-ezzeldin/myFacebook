package com.example.myfacebook.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfacebook.R
import com.example.myfacebook.models.PostModel
import java.util.ArrayList

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var dataList : MutableList<PostModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postId.text = (dataList[position].id).toString()
        holder.postTitle.text = dataList[position].title
        holder.postBody.text = dataList[position].body
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(list :MutableList<PostModel>) {
        this.dataList.clear()
        this.dataList.addAll(list)
        notifyDataSetChanged()
        Log.d("TAG", "setList: $itemCount")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postId = itemView.findViewById<TextView>(R.id.post_id)
        val postTitle = itemView.findViewById<TextView>(R.id.post_title)
        val postBody = itemView.findViewById<TextView>(R.id.post_body)

    }
}
