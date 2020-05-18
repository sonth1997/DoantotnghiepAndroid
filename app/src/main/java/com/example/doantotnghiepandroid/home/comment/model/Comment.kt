package com.example.doantotnghiepandroid.home.comment.model

data class Comment (val id: String?, val email: String, val time: Long, val text : String){
    constructor() : this("", "", 0, "")
}