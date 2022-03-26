package com.example.rickandmorty2.data.remote.dtos.episode

import com.google.gson.annotations.SerializedName

data class RickAndMortyEpisode (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episodes")
    val episode: String
)