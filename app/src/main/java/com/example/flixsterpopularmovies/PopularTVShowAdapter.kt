package com.example.flixsterpopularmovies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PopularTVShowAdapter(private var mMovies: List<Movie>): RecyclerView.Adapter<PopularTVShowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.movie_image_imageView)
        val movieName: TextView = itemView.findViewById(R.id.movie_title_textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val wishListView = inflater.inflate(R.layout.movie, parent, false)
        return ViewHolder(wishListView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = mMovies[position]

        holder.movieName.text = movie.name

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(com.facebook.stetho.R.drawable.abc_ab_share_pack_mtrl_alpha)
            .centerInside()
            .into(holder.movieImage)

    }

    override fun getItemCount(): Int {
        return mMovies.size
    }
}