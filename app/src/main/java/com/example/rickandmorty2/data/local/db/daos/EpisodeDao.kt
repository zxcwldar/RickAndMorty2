package com.example.rickandmorty2.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty2.data.remote.dtos.character.RickAndMortyCharacter
import com.example.rickandmorty2.data.remote.dtos.episode.RickAndMortyEpisode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisode(vararg episode: RickAndMortyEpisode)

    @Query("SELECT * FROM rickandmortyepisode" )
    suspend fun getAllEpisode() : List<RickAndMortyEpisode>

}