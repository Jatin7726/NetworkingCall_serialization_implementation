
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.model.MarsPhoto

import com.example.marsphotos.network.MarshApi

import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface MarsUIState{
    data class Success(val photos: List<MarsPhoto>): MarsUIState
    object Error: MarsUIState
    object Loading: MarsUIState
}

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUIState  by mutableStateOf(MarsUIState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {

        viewModelScope.launch {
            marsUiState = MarsUIState.Loading
            marsUiState = try {
                val listResult = MarshApi.retrofitService.getPhotos()
                MarsUIState.Success(listResult)

            } catch (e: IOException) {
                MarsUIState.Error
            } catch (e: HttpException) {
                MarsUIState.Error
            }
        }
    }
}
