package com.example.flixsterpopularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.flixsterpopularmovies.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the intent which is serializable and cast into the movie class
        // Both popular and cinema movie use the same put extra (MOVIE_EXTRA)
        // And they use the same xml file (activity_detail.xml), so we just
        // Update one time, no matter which one is clicked in the main activity
        // The extras will be passed and the textview upgraded appropriately
        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        // Update the overview textview and use glide to upload the image
        binding.overviewTextView.text = movie.overview

        val a = movie.title.toString().length
        val b = movie.name.toString().length

        if (a > b){

            binding.titleTextView.text = movie.title
        }
        else{

            binding.titleTextView.text = movie.name
        }


        val radius = 50
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(binding.movieDetailImage)

    }
}