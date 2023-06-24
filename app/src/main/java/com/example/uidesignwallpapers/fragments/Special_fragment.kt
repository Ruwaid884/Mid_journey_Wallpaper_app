package com.example.uidesignwallpapers.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesignwallpapers.Image
import com.example.uidesignwallpapers.R
import com.example.uidesignwallpapers.adapters.special_recycler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class special_fragment:Fragment(R.layout.special) {
    private lateinit var specialrecycler: RecyclerView
    private lateinit var specialadapter: special_recycler
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.special,container,false)
        specialrecycler = rootview.findViewById(R.id.special_recycler)
        val database:FirebaseDatabase = FirebaseDatabase.getInstance()
        val images = mutableListOf<Image>()
        val imagesref = database.getReference("specialimages")
        specialadapter= special_recycler(images,requireContext())

        specialrecycler.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        specialrecycler.adapter = specialadapter


        imagesref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                images.clear()
                for (imageSnapshot in snapshot.children) {
                    val image = imageSnapshot.getValue(Image::class.java)
                    images.add(image!!)
                }
                specialadapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })
        return rootview


}
}