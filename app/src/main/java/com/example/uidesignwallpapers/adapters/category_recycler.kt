package com.example.uidesignwallpapers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uidesignwallpapers.Image
import com.example.uidesignwallpapers.Image_category
import com.example.uidesignwallpapers.R
import kotlin.math.min

class category_recycler(private val images:List<Image_category>): RecyclerView.Adapter<category_recycler.MyViewHolder>() {


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.category_recycler_image)
        val textview:TextView = itemView.findViewById(R.id.category_recycler_text)
        val image2 :ImageView = itemView.findViewById(R.id.category_recycler_image2)
        val textview2:TextView= itemView.findViewById(R.id.category_recycler_text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_recycler,
            parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size/2 + images.size%2
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val images = images.subList(position * 2, min(position * 2 + 2, images.size))
        for (i in images.indices  ) {
            when (i) {
                0 -> {
                    Glide.with(holder.itemView).load(images[i].url).into(holder.image)
                holder.textview.text = images[i].text}
                1 ->{
                    Glide.with(holder.itemView).load(images[i].url).into(holder.image2)
                    holder.textview2.text = images[i].text
                }
            }

        }
    }

}