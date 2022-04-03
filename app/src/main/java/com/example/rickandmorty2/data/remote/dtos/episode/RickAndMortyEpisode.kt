package com.example.rickandmorty2.data.remote.dtos.episode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty2.base.BaseDiffModel
import com.google.gson.annotations.SerializedName
@Entity
data class RickAndMortyEpisode(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    override val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("air_date")
    val air_date: String,

    @SerializedName("episode")
    val episode: String
) : BaseDiffModel