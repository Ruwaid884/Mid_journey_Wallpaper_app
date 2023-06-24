package com.example.uidesignwallpapers.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.uidesignwallpapers.Image
import com.example.uidesignwallpapers.Image_category
import com.example.uidesignwallpapers.R
import com.example.uidesignwallpapers.adapters.Horizontal_recycler
import com.example.uidesignwallpapers.adapters.category_recycler
import com.example.uidesignwallpapers.adapters.screen_adapter_category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay

class categories_fragment : Fragment(R.layout.categories_fragment) {
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var category_adapter: category_recycler
    private lateinit var viewpager:ViewPager2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.categories_fragment,container,false)
        viewpager = rootview.findViewById(R.id.category_screen)
        val imageList = listOf(R.drawable.img1,R.drawable.img)
        val screen_adapter = screen_adapter_category(imageList)
        viewpager.adapter = screen_adapter
        viewpager.autoscroll(lifecycleScope,3000)
        categoryRecycler = rootview.findViewById(R.id.category_recycler)
        categoryRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
      val database:FirebaseDatabase=FirebaseDatabase.getInstance()
      val listcategory= mutableListOf<Image_category>()
      val imagesref = database.getReference("categorychoices")


        imagesref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                listcategory.clear()
                for (imageSnapshot in dataSnapshot.children) {
                    val image = imageSnapshot.getValue(Image_category::class.java)
                    listcategory.add(image!!)
                }
                category_adapter.notifyDataSetChanged()
            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        category_adapter= category_recycler(listcategory)
        categoryRecycler.adapter= category_adapter
        return rootview

    }

    fun ViewPager2.autoscroll(lifecycleScope:LifecycleCoroutineScope,interval:Long) {
    lifecycleScope.launchWhenResumed {
        scroll(interval)
    }

    }
}

    private suspend fun ViewPager2.scroll(interval:Long){
        delay(interval)
        val numberofitems = adapter?.itemCount?:0
        val lastIndex = if(numberofitems>0) numberofitems -1 else 0
        val nextItem = if(currentItem==lastIndex) 0 else currentItem +1

        setCurrentItem(nextItem,true)
        scroll(interval)
    }