package com.jikanAPI.presentation.detail

import com.jikanAPI.domain.model.Anime

data class AnimeDetailState(
    val isLoading: Boolean = false,
    val anime: Anime? = null,
    val error: String = ""
)
