package com.jikanAPI.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jikanAPI.domain.model.Anime

@Entity
data class AnimeEntity(
    @PrimaryKey val malId: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val imageUrl: String,
    val synopsis: String?,
    val genres: String, // Storing as comma separated string for simplicity
    val trailerUrl: String?
) {
    fun toAnime(): Anime {
        return Anime(
            malId = malId,
            title = title,
            episodes = episodes,
            score = score,
            imageUrl = imageUrl,
            synopsis = synopsis,
            genres = if (genres.isBlank()) emptyList() else genres.split(","),
            cast = emptyList(),
            trailerUrl = trailerUrl
        )
    }
}
