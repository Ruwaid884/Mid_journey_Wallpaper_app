package com.example.uidesignwallpapers.adapters

import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesignwallpapers.R

class screen_adapter_category(val imagelist:List<Int>): RecyclerView.Adapter<screen_adapter_category.MyViewHolder>() {


    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val image :ImageView= view.findViewById(R.id.image_category_screen)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_screen_category,
            parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val list= imagelist[position]
        holder.image.setImageResource(list)
    }

    override fun getItemCount(): Int {
        return imagelist.size
    }

}