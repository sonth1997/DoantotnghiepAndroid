package com.example.doantotnghiepandroid.home.comment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.home.comment.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*
import java.text.SimpleDateFormat

class CommentAdapter (var messenger : ArrayList<Comment> = arrayListOf(), private val mlistener: onClickItemMessenger)
    : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return messenger.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(messenger[position])
        holder.itemView.setOnClickListener {
            mlistener.onClickItemMain(messenger[position])
        }
    }

    fun setData(list: ArrayList<Comment>){
        this.messenger.clear()
        this.messenger.addAll(list)
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(comment: Comment) {
            val simpleDate =  SimpleDateFormat("hh:mm:ss")
            itemView.tvComment.text = comment.text
            itemView.tvNameEmail.text = comment.email
            itemView.tvTimeComment.text = simpleDate.format(comment.time)
        }
    }
    interface onClickItemMessenger {
        fun onClickItemMain(comment: Comment)
    }
}