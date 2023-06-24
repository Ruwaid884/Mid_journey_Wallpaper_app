package com.example.uidesignwallpapers

import android.app.Activity
import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import androidx.transition.Transition
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yalantis.ucrop.UCrop
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.net.URL

class image_full_size_avtivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full_size_avtivity)
        val download: FloatingActionButton = findViewById(R.id.download)
        val image_url = intent.getStringExtra("url")
        val image: ImageView = findViewById(R.id.full_size_image)

        Glide.with(this).load(image_url).into(image)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels


        download.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.SET_WALLPAPER
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.SET_WALLPAPER),
                    0
                )
            } else {
                // Permission is already granted, continue to setting wallpaper

// Download the image from the URL using Glide
                val wallpaperManager = WallpaperManager.getInstance(this)


                val options = arrayOf("Lock Screen", "Home Screen")
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Select a wallpaper type")
                builder.setItems(options) { _, which ->
                    when (which) {
                        0 -> {
                            // do something for lock screen
                            val setWallpaperTask = SetWallpaperTask(wallpaperManager, this, 0,width,height)
                            setWallpaperTask.execute("$image_url")

                        }
                        1 -> {
                            // do something for home screen
                            val setWallpaperTask = SetWallpaperTask(wallpaperManager, this, 1,width,height)
                            setWallpaperTask.execute("$image_url")
                        }

                    }
                }
                val dialog = builder.create()
                dialog.show()


            }


        }


        val crop: FloatingActionButton = findViewById(R.id.crop)

        crop.setOnClickListener {

            fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
                return outputStream.toByteArray()
            }

            val options = UCrop.Options()
            options.setCompressionFormat(Bitmap.CompressFormat.PNG)
            Glide.with(this)
                .asBitmap()
                .load("$image_url")
                .into(object : CustomTarget<Bitmap>() {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        val inputStream = ByteArrayInputStream(getBitmapAsByteArray(resource))
                        val byteArrayUri = Uri.parse(
                            MediaStore.Images.Media.insertImage(
                                contentResolver,
                                BitmapFactory.decodeStream(inputStream),
                                "Title",
                                null
                            )
                        )
                        val destinationUri = Uri.fromFile(File(cacheDir, "cropped"))
                        val uCrop = UCrop.of(byteArrayUri, destinationUri)
                            .withOptions(options)
                        startActivityForResult(
                            uCrop.getIntent(this@image_full_size_avtivity),
                            UCrop.REQUEST_CROP
                        )
                        val originalFile = File(cacheDir, "original_image.png")
                        originalFile.delete()
                        val croppedFile = File(cacheDir, "cropped.png")
                        croppedFile.delete()
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })


        }


    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            val resultUri = UCrop.getOutput(data!!)
            val wallpaperManager = WallpaperManager.getInstance(this)
            val inputStream = resultUri?.let { contentResolver.openInputStream(it) }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    }

