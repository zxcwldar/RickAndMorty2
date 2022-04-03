package com.example.rickandmorty2.presentation.ui.fragments.episode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty2.base.BaseViewModel
import com.example.rickandmorty2.common.resourse.Resource
import com.example.rickandmorty2.data.remote.dtos.RickAndMortyResponse
import com.example.rickandmorty2.data.remote.dtos.episode.RickAndMortyEpisode
import com.example.rickandmorty2.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : BaseViewModel() {

    private var page = 1
    var isLoading: Boolean = false

    private val _episodesState = MutableLiveData<RickAndMortyResponse<RickAndMortyEpisode>>()
    val episodesState: LiveData<RickAndMortyResponse<RickAndMortyEpisode>> = _episodesState

    private val _episodesLocaleState = MutableLiveData<List<RickAndMortyEpisode>>()
    val episodesLocaleState: LiveData<List<RickAndMortyEpisode>> = _episodesLocaleState

    fun fetchEpisodes() {
        isLoading = true
        episodeRepository.fetchEpisode(page).collect(_episodesState) {
            page++
            isLoading = false
        }
    }
    
    fun getEpisodes() = episodeRepository.getEpisodes().collect(_episodesLocaleState, null)
}

