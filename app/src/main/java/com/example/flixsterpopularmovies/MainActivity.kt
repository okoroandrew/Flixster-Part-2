package com.example.flixsterpopularmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixsterpopularmovies.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers



private const val TAG = "MainActivity/"
private const val API_KEY = "34e61e3ddd6944220f1e09bcfb1b726d"
private const val MOVIE_SEARCH_URL =
    "https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}"
private const val TVSHOW_SEARCH_URL =
    "https://api.themoviedb.org/3/tv/popular?api_key=${API_KEY}"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var tvShowRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the horizontal linear layout manager
        val horizontalLayoutManager = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        val horizontalLayoutManager2 = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        // Set up the popular movies recycler view and adapter
        moviesRecyclerView = binding.popularMoviesRecyclerView
        moviesRecyclerView.layoutManager = horizontalLayoutManager
        updateAdapter(moviesRecyclerView, MOVIE_SEARCH_URL)

        // Set up the popular tv show and recycler view and adapter
        tvShowRecyclerView = binding.popularTVShowRecyclerView
        tvShowRecyclerView.layoutManager = horizontalLayoutManager2
        updateAdapter(tvShowRecyclerView, TVSHOW_SEARCH_URL)

    }

    private fun updateAdapter(recyclerView1: RecyclerView ,movie_search_url: String) {

        val client = AsyncHttpClient()
        client.get(movie_search_url, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch movie: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JsonHttpResponseHandler.JSON) {
                Log.i(TAG, "Successfully fetched movies: $json")
                val resultJson = json.jsonObject.get("results").toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                val models: List<Movie> = gson.fromJson(resultJson, arrayMovieType)
                //recyclerView1.adapter = PopularMoviesAdapter(models)
                when (recyclerView1){
                    tvShowRecyclerView -> recyclerView1.adapter = PopularTVShowAdapter(models)
                    moviesRecyclerView -> recyclerView1.adapter = PopularMoviesAdapter(models)
                }
            }

        })
    }
}