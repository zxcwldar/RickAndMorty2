package com.example.rickandmorty2.presentation.ui.fragments.episode

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty2.R
import com.example.rickandmorty2.base.BaseFragment
import com.example.rickandmorty2.common.expensions.submitData
import com.example.rickandmorty2.databinding.FragmentEpisodeBinding
import com.example.rickandmorty2.presentation.ui.adapters.EpisodeAdapter
import com.example.rickandmorty2.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(
    R.layout.fragment_episode
) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodesAdapter = EpisodeAdapter()

    override fun setupViews() {
        setupAdapter()
    }

    override fun setupObserver() {
        subscribeToEpisodes()
        subscribeToEpisodesLocale()
    }

    private fun setupAdapter() = with(binding.recyclerview) {
        adapter = episodesAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) viewModel.fetchEpisodes()
                else null
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToEpisodes() {
        viewModel.episodesState.observe(viewLifecycleOwner) {
            episodesAdapter.submitData(it.results)
        }
    }

    private fun subscribeToEpisodesLocale() {
        viewModel.episodesLocaleState.observe(viewLifecycleOwner) {
            episodesAdapter.submitData(it)
        }
    }

    override fun setupRequest() {
        if (viewModel.episodesState.value == null && isOnline()) viewModel.fetchEpisodes()
        else viewModel.getEpisodes()
    }

    fun isOnline(): Boolean {
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}

