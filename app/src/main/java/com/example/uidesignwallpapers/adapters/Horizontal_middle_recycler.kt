package com.example.uidesignwallpapers.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uidesignwallpapers.Image
import com.example.uidesignwallpapers.R
import com.example.uidesignwallpapers.horizontal_upper_recycler_activity

class Horizontal_middle_recycler(private val images:List<Image>,private val context: Context): RecyclerView.Adapter<Horizontal_middle_recycler.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image_middle_recycler)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.middle_recycler,
            parent, false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = images[position]
        Glide.with(holder.itemView.context).load(image.url).into(holder.image)
        holder.image.setOnClickListener {
            val intent = Intent(context, horizontal_upper_recycler_activity::class.java)
            intent.putExtra("position_middle", position)
            context.startActivity(intent)
        }

    }
}