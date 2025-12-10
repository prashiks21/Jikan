package com.jikanAPI.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jikanAPI.common.Resource
import com.jikanAPI.domain.usecase.GetAnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AnimeDetailState())
    val state: State<AnimeDetailState> = _state

    init {
        savedStateHandle.get<String>("animeId")?.let { animeId ->
            getAnimeDetails(animeId.toInt())
        }
    }

    private fun getAnimeDetails(animeId: Int) {
        getAnimeDetailsUseCase(animeId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AnimeDetailState(anime = result.data)
                }
                is Resource.Error -> {
                    _state.value = AnimeDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
