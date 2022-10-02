package com.example.flixsterpopularmovies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


const val MOVIE_EXTRA2 = "MOVIE_EXTRA"

class PopularTVShowAdapter(private var mMovies: List<Movie>, private val context: Context): RecyclerView.Adapter<PopularTVShowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val movieImage: ImageView = itemView.findViewById(R.id.movie_image_imageView)
        val movieName: TextView = itemView.findViewById(R.id.movie_title_textView)

        init {
            itemView.setOnClickListener(this)
        }

        //what happens when a view is clicked
        override fun onClick(view: View) {
            // Get selected movie
            val movie = mMovies[absoluteAdapterPosition]

            //Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA2, movie)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, view, "movie_poster")
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val wishListView = inflater.inflate(R.layout.movie, parent, false)
        return ViewHolder(wishListView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = mMovies[position]

        holder.movieName.text = movie.name

        val radius = 50
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(com.facebook.stetho.R.drawable.abc_ab_share_pack_mtrl_alpha)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(holder.movieImage)

    }

    override fun getItemCount(): Int {
        return mMovies.size
    }
}