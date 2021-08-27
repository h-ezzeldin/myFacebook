package com.example.myfacebook.models

class PostModel {
    var id: Int? = null
    var userId: Int? = null
    var title: String
    var body: String

    constructor( id: Int, userId: Int ,  title: String,  body: String )  {
        this.title = title
        this.body = body
        this.userId = userId
        this.id = id

    }

    constructor(userId: Int, title: String, body: String) {
        this.title = title
        this.body = body
        this.userId = userId

    }

}