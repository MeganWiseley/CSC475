package com.example.photogalleryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the layout

        recyclerView = findViewById(R.id.photoRecyclerView) // Initialize RecyclerView

        // Set the GridLayoutManager programmatically
        val layoutManager = GridLayoutManager(this, 3) // 3 columns in the grid
        recyclerView.layoutManager = layoutManager

        // Set up the adapter with the image URLs
        val imageUrls = listOf(
            "https://megancookphotography.com/wp-content/uploads/2021/07/DSC_0267-1024x683.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0188-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0170-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0181-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0075-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC2261-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC2277-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC2235-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0195.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0135-678x1024.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0162-1024x678.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0187-980x649.jpg"

        )

        photoAdapter = PhotoAdapter(imageUrls)

        recyclerView.adapter = photoAdapter
    }
}
