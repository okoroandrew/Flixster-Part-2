package com.example.flixsterpopularmovies

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
class Movie (
    @JvmField
    @SerialName("title")
    var title: String? = null,

    @SerialName("name")
    var name: String? = null,

    @SerialName("poster_path")
    var poster_path: String? = null,

    @SerialName("overview")
    var overview: String? = null
) : java.io.Serializable{

}
