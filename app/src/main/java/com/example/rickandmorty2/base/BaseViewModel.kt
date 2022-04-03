package com.example.rickandmorty2.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty2.common.resourse.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(){
    protected fun <T> Flow<Resource<T>>.collect(
        state: MutableLiveData<T>,
        addition: (() -> Unit)? = null

    ) {

        viewModelScope.launch {
            collect {
                when (it) {
                    is Resource.Loading -> {
                        Log.e("anime", " ")
                    }
                    is Resource.Error -> {
                        Log.e(" JoJo", it.message.toString())
                    }
                    is Resource.Success -> {
                        addition?.let { addition() }
                        state.postValue(it.data)

                    }
                }
            }
        }


    }
}
