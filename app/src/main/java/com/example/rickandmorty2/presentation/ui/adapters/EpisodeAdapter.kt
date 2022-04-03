package com.example.rickandmorty2.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty2.base.BaseDiffUtil
import com.example.rickandmorty2.data.remote.dtos.episode.RickAndMortyEpisode
import com.example.rickandmorty2.databinding.ItemEpisodeBinding

class EpisodeAdapter :
    ListAdapter<RickAndMortyEpisode, EpisodeAdapter.EpisodeViewHolder>(
        BaseDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }

    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(rickAndMortyEpisode: RickAndMortyEpisode) {

            binding.tvName.text = rickAndMortyEpisode.name
            binding.tvAirDate.text = rickAndMortyEpisode.air_date
            binding.tvEpisode.text = rickAndMortyEpisode.episode


        }

    }


}

