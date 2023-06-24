package com.example.uidesignwallpapers
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.IOException

class SetWallpaperTask(private val wallpaperManager: WallpaperManager,private val context: Context,private val int:Int,private val width:Int,
                       private val height:Int) {
    fun execute(url:String) {

        Glide.with(context)
            .asBitmap()
            .load(url)
            .override(width, height)
            .into(object : CustomTarget<Bitmap>() {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    try {
                        if(int ==0){
                        wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_LOCK)
                        Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show()}
                        if(int ==1){
                            wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_SYSTEM)
                            Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show()}
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(context, "Error setting wallpaper", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap





                }
            })
    }
}

