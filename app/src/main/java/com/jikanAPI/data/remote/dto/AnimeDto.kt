package com.jikanAPI.data.remote.dto

import com.jikanAPI.domain.model.Anime
import com.squareup.moshi.Json

data class AnimeDto(
    @Json(name = "mal_id") val malId: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    @Json(name = "images") val images: ImagesDto,
    val synopsis: String?,
    val genres: List<GenreDto>?,
    val trailer: TrailerDto?
) {
    fun toAnime(): Anime {
        return Anime(
            malId = malId,
            title = title,
            episodes = episodes,
            score = score,
            imageUrl = images.jpg.imageUrl,
            synopsis = synopsis,
            genres = genres?.map { it.name } ?: emptyList(),
            cast = emptyList(), // Placeholder as it requires separate endpoint
            trailerUrl = trailer?.url
        )
    }
}

data class ImagesDto(
    val jpg: JpgDto
)

data class JpgDto(
    @Json(name = "image_url") val imageUrl: String
)

data class GenreDto(
    val name: String
)

data class TrailerDto(
    val url: String?
)

data class AnimeResponse(
    val data: List<AnimeDto>
)

data class SingleAnimeResponse(
    val data: AnimeDto
)
