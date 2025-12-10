package com.jikanAPI.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jikanAPI.common.Resource
import com.jikanAPI.domain.usecase.GetTopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnimeUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AnimeListState())
    val state: State<AnimeListState> = _state

    init {
        getTopAnime()
    }

    private fun getTopAnime() {
        getTopAnimeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AnimeListState(anime = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AnimeListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
