package com.example.doantotnghiepandroid.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.home.model.Home
import com.example.doantotnghiepandroid.loadImage
import com.google.firebase.database.core.Context
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter ( var home : ArrayList<Home>, var mlistener:onClickItemHome): RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false))
    }

    override fun getItemCount(): Int {
        return home.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(home[position])
        holder.itemView.setOnClickListener {
            mlistener.onClickItemHome(home[position])
        }
    }
    fun setData(list: ArrayList<Home>){
        this.home = list
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun onBind(home: Home){
            itemView.imgBook.loadImage(home.image)
            itemView.tvTitle.text = home.data
            itemView.tvDate.text = home.title
        }
    }
    interface onClickItemHome{
        fun onClickItemHome(home : Home)
    }
}