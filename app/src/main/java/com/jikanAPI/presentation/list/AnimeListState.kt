package com.jikanAPI.presentation.list

import com.jikanAPI.domain.model.Anime

data class AnimeListState(
    val isLoading: Boolean = false,
    val anime: List<Anime> = emptyList(),
    val error: String = ""
)
