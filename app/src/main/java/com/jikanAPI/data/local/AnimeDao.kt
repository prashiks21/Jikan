package com.jikanAPI.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jikanAPI.data.local.entity.AnimeEntity

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(animeList: List<AnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Query("SELECT * FROM AnimeEntity")
    suspend fun getAnimeList(): List<AnimeEntity>

    @Query("SELECT * FROM AnimeEntity WHERE malId = :id")
    suspend fun getAnime(id: Int): AnimeEntity?

    @Query("DELETE FROM AnimeEntity")
    suspend fun clearAnimeList()
}
