package com.example.rickandmorty2.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty2.data.remote.dtos.character.RickAndMortyCharacter
import com.example.rickandmorty2.data.remote.dtos.location.RickAndMortyLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocation(vararg locations: RickAndMortyLocation)

    @Query("SELECT *FROM rickandmortylocation ")
    suspend fun getAllLocation(): List<RickAndMortyLocation>
}
