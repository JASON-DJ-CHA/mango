package com.jason.example.mangoapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class RVAdapter(val context : Context, val List : MutableList<ContentsModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int):RVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent,false)

        return  ViewHolder(v)
    }

    interface  ItemClick{
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick?= null

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {

        if(itemClick != null){
            holder.itemView.setOnClickListener { v->
                itemClick!!.onClick(v,position)
            }
        }
        holder.bindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : ContentsModel){

            val rvText = itemView.findViewById<TextView>(R.id.rvTextArea)

            val rvImg = itemView.findViewById<ImageView>(R.id.rvImageArea)

            rvText.text = item.titleText
            Glide.with(context)
                .load(item.ImageURL)
                .into(rvImg)
        }
    }
}