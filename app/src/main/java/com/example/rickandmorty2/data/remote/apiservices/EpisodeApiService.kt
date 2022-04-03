package com.example.rickandmorty2.data.remote.apiservices

import com.example.rickandmorty2.data.remote.dtos.RickAndMortyResponse
import com.example.rickandmorty2.data.remote.dtos.episode.RickAndMortyEpisode
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("api/episode")
    suspend fun fetchEpisodes(@Query("page") page: Int): RickAndMortyResponse<RickAndMortyEpisode>
}