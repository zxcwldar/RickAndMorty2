package com.example.rickandmorty2.data.remote.dtos.character

import com.google.gson.annotations.SerializedName

data class Origin (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)