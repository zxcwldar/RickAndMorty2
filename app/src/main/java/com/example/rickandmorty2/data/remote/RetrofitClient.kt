package com.example.rickandmorty2.data.remote

import com.example.rickandmorty2.common.constants.Constants.BASE_URL
import com.example.rickandmorty2.data.remote.apiservices.CharacterApiService
import com.example.rickandmorty2.data.remote.apiservices.EpisodeApiService
import com.example.rickandmorty2.data.remote.apiservices.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideCharactersApiService(): CharacterApiService =
        retrofit.create(CharacterApiService::class.java)


    fun provideLocationApiService() =
        retrofit.create(LocationApiService::class.java)

    fun provideEpisodesApiService() =
        retrofit.create(EpisodeApiService::class.java)


}