package com.example.uidesignwallpapers.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uidesignwallpapers.Image
import com.example.uidesignwallpapers.R
import com.example.uidesignwallpapers.fragments.home_fragment
import com.example.uidesignwallpapers.horizontal_upper_recycler_activity
import kotlinx.coroutines.withContext

class Horizontal_recycler(private val images:List<Image>,
private val context: Context): RecyclerView.Adapter<Horizontal_recycler.MyViewHolder>() {


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
val image :ImageView = itemView.findViewById(R.id.image_upper_recycler)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upper_recycler,
            parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val image = images[position]
         Glide.with(holder.itemView.context).load(image.url).into(holder.image)
        holder.image.setOnClickListener{
       val intent = Intent(context,horizontal_upper_recycler_activity::class.java)
            intent.putExtra("position",position)
            context.startActivity(intent)
        }
    }

}