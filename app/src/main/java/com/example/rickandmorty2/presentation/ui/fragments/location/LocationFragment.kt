package com.example.rickandmorty2.presentation.ui.fragments.location

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty2.R
import com.example.rickandmorty2.base.BaseFragment
import com.example.rickandmorty2.common.expensions.submitData
import com.example.rickandmorty2.databinding.FragmentLocationBinding
import com.example.rickandmorty2.presentation.ui.adapters.LocationAdapter
import com.example.rickandmorty2.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding, LocationViewModel>(
    R.layout.fragment_location
) {
    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter()

    override fun setupObserver() {
        subscribeToLocations()
        subscribeToLocationLocale()
    }

    override fun setupViews() {
        setupAdapter()
    }

    private fun setupAdapter() = with(binding.recyclerview) {
        adapter = locationAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager, {
                if (isOnline()) viewModel.fetchLocation()
                else null
            }) {
            override fun isLoading() = viewModel.isLoading
        })
    }

    private fun subscribeToLocations() {
        viewModel.locationsState.observe(viewLifecycleOwner) {
            locationAdapter.submitData(it.results)

        }
    }

    private fun subscribeToLocationLocale() {
        viewModel.episodesLocaleState.observe(viewLifecycleOwner) {
            locationAdapter.submitData(it)
        }
    }

    override fun setupRequest() {
        if (viewModel.locationsState.value == null && isOnline()) viewModel.fetchLocation()
        else viewModel.getLocation()
    }

    fun isOnline(): Boolean {
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}