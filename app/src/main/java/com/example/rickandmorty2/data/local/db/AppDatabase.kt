package com.example.rickandmorty2.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty2.data.local.db.daos.CharacterDao
import com.example.rickandmorty2.data.local.db.daos.EpisodeDao
import com.example.rickandmorty2.data.local.db.daos.LocationDao
import com.example.rickandmorty2.data.remote.dtos.character.RickAndMortyCharacter
import com.example.rickandmorty2.data.remote.dtos.episode.RickAndMortyEpisode
import com.example.rickandmorty2.data.remote.dtos.location.RickAndMortyLocation

@Database(entities = [RickAndMortyCharacter::class, RickAndMortyEpisode::class, RickAndMortyLocation::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao

    abstract fun locationDao(): LocationDao


}
