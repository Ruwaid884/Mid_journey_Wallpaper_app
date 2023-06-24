package com.example.uidesignwallpapers

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.mbms.StreamingServiceInfo
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.uidesignwallpapers.adapters.adapter_inside_upper_hrecycler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class horizontal_upper_recycler_activity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter:adapter_inside_upper_hrecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_upper_recycler)

       val position = intent.getIntExtra("position",-1)
        val position_middle = intent.getIntExtra("position_middle",-1)
        val  images = mutableListOf<Image>()
        val database:FirebaseDatabase = FirebaseDatabase.getInstance()
        var imagesref = database.getReference("specialimages")

        val ref = listOf<String>("specialimages","homelowerrecycler","specialimages")
        var string :String = ref[0]


        val refmiddle = listOf<String>("homelowerrecycler","specialimages","homelowerrecycler","specialimages","homelowerrecycler","homelowerrecycler","specialimages")
       var size = 0
        if(position==-1)
            size = refmiddle.size
        else
            size = ref.size

        for( i in 0 until size){

            if(i==position ){

                string = ref[position]
                imagesref = database.getReference("$string")
                break
            }
            else
                if(i == position_middle){
                    string = refmiddle[i]
                    imagesref = database.getReference("$string")
                    break
                }
        }



        recycler = findViewById(R.id.inside_horizontal_upper_recycler)
        recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapter = adapter_inside_upper_hrecycler(images,this)
        imagesref.addValueEventListener(object : ValueEventListener {
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
        recycler.adapter = adapter


    }
}