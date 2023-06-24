package com.example.uidesignwallpapers.Adapter

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
import com.example.uidesignwallpapers.adapters.Horizontal_recycler
import com.example.uidesignwallpapers.image_full_size_avtivity
import java.util.Collections.min
import kotlin.math.min

class Horizontal_lower_recycler(private val images:List<Image>,private val context: Context): RecyclerView.Adapter<Horizontal_lower_recycler.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image1: ImageView = itemView.findViewById(R.id.lower_image1)
        val image2: ImageView = itemView.findViewById(R.id.lower_image2)
        val image3: ImageView = itemView.findViewById(R.id.lower_image3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.bootm_recycler,
            parent, false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size / 3 + images.size % 3
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val images = images.subList(position * 3, min(position * 3 + 3, images.size))
        for (i in images.indices) {
            when (i) {
                0 -> {
                    Glide.with(holder.itemView).load(images[i].url).into(holder.image1)

                    holder.image1.setOnClickListener {
                        val intent = Intent(context, image_full_size_avtivity::class.java)
                        intent.putExtra("url", images[i].url)
                        context.startActivity(intent)
                    }


                }
                1 -> {
                    Glide.with(holder.itemView).load(images[i].url).into(holder.image2)
                    holder.image2.setOnClickListener {
                        val intent = Intent(context, image_full_size_avtivity::class.java)
                        intent.putExtra("url", images[i].url)
                        context.startActivity(intent)
                    }
                }
                2 -> {
                    Glide.with(holder.itemView).load(images[i].url).into(holder.image3)
                    holder.image3.setOnClickListener {
                        val intent = Intent(context, image_full_size_avtivity::class.java)
                        intent.putExtra("url", images[i].url)
                        context.startActivity(intent)
                    }
                }

            }
        }
    }
}


