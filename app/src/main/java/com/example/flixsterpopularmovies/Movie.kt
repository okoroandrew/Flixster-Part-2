package com.example.flixsterpopularmovies

import com.google.gson.annotations.SerializedName

class Movie {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("poster_path")
    var poster_path: String? = null

    @SerializedName("overview")
    var overview: String? = null
}
