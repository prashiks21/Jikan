package com.jikanAPI.domain.model

data class Anime(
    val malId: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val imageUrl: String,
    val synopsis: String? = null,
    val genres: List<String> = emptyList(),
    val cast: List<String> = emptyList(),
    val trailerUrl: String? = null
)
