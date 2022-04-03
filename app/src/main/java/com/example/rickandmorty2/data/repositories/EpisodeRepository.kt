package com.example.rickandmorty2.data.repositories

import com.example.rickandmorty2.base.BaseRepository
import com.example.rickandmorty2.data.local.db.daos.EpisodeDao
import com.example.rickandmorty2.data.remote.apiservices.EpisodeApiService
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService,
    private val episodeDao: EpisodeDao
) :
    BaseRepository() {

    fun fetchEpisode(page: Int) = doRequest(
        { service.fetchEpisodes(page) },
        { episodes -> episodeDao.insertAllEpisode(* episodes.results.toTypedArray()) })


    fun getEpisodes() = doRequest {
        episodeDao.getAllEpisode()
    }
}