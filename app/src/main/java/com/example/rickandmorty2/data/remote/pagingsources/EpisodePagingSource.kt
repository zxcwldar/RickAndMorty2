package com.example.rickandmorty2.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.rickandmorty2.data.remote.apiservices.EpisodeApiService
import com.example.rickandmorty2.data.remote.dtos.episode.RickAndMortyEpisode
import java.io.IOException

class EpisodePagingSource (private val service: EpisodeApiService) :
    PagingSource<Int, RickAndMortyEpisode>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyEpisode> {
        val page = params.key ?: 1
         try {
            val response = service.fetchEpisodes(page)
            val nextPageNumber = Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            return LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyEpisode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}