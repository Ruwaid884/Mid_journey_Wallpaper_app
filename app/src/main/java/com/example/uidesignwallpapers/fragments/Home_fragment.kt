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
import com.example.uidesignwallpapers.Adapter.Horizontal_lower_recycler
import com.example.uidesignwallpapers.Image
import com.example.uidesignwallpapers.R
import com.example.uidesignwallpapers.adapters.Horizontal_middle_recycler
import com.example.uidesignwallpapers.adapters.Horizontal_recycler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class home_fragment: Fragment(R.layout.home_fragment) {
    // setting the homefragment
    private var lastY =0f
    private lateinit var Horizontal_recycler:RecyclerView
    private lateinit var adapter: Horizontal_recycler
    private lateinit var Horizontal_middle_recycler:RecyclerView
    private lateinit var adaptermiddle: Horizontal_middle_recycler

    private lateinit var Horizontal_lower_recycler:RecyclerView
    private lateinit var adapterlower:Horizontal_lower_recycler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val images = mutableListOf<Image>()
        val database = FirebaseDatabase.getInstance()
        val imagesRef = database.getReference("horizontalrecycler")
        val rootview = inflater.inflate(R.layout.home_fragment,container,false)

        Horizontal_recycler = rootview.findViewById(R.id.homeupperrecycler)



        Horizontal_recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        adapter = Horizontal_recycler(images,requireContext())

        Horizontal_recycler.adapter = adapter

        imagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                images.clear()
                for (imageSnapshot in dataSnapshot.children) {
                    val image = imageSnapshot.getValue(Image::class.java)
                    images.add(image!!)
                }
                adapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        val imagesmiddle = mutableListOf<Image>()
        Horizontal_middle_recycler = rootview.findViewById(R.id.homemiddlerecycler)
        adaptermiddle = Horizontal_middle_recycler(imagesmiddle,requireContext())


        val middleimageref = database.getReference("homemiddlerecycler")





        middleimageref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                imagesmiddle.clear()
                for (imageSnapshot in dataSnapshot.children) {
                    val image = imageSnapshot.getValue(Image::class.java)
                    imagesmiddle.add(image!!)
                }
                adaptermiddle.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })





        Horizontal_middle_recycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        Horizontal_middle_recycler.adapter = adaptermiddle



        val imageslower = mutableListOf<Image>()
        val lowerimageref = database.getReference("homelowerrecycler")

        Horizontal_lower_recycler = rootview.findViewById(R.id.bottom_recycler)
        Horizontal_lower_recycler.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapterlower=Horizontal_lower_recycler(imageslower,requireContext())
        Horizontal_lower_recycler.adapter= adapterlower




        lowerimageref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                imageslower.clear()
                for (imageSnapshot in dataSnapshot.children) {
                    val image = imageSnapshot.getValue(Image::class.java)
                    imageslower.add(image!!)
                }
                adapterlower.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })



        return rootview
    }
}

