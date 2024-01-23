package com.banklannister.marsrover.ui.savedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banklannister.marsrover.data.MarsRoverPhotoRepo
import com.banklannister.marsrover.di.IoDispatchers
import com.banklannister.marsrover.domain.RoverPhotoUiModel
import com.banklannister.marsrover.domain.RoverPhotoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val marsRoverPhotoRepo: MarsRoverPhotoRepo,
    @IoDispatchers private val ioDispatchers: CoroutineDispatcher
) : ViewModel() {

    private val _marsPhotoSavedState: MutableStateFlow<RoverPhotoUiState> =
        MutableStateFlow(RoverPhotoUiState.Loading)

    val marsPhotoSavedState: StateFlow<RoverPhotoUiState>
        get() = _marsPhotoSavedState

    fun getAllSaved() {
        viewModelScope.launch(ioDispatchers) {
            marsRoverPhotoRepo.getAllSaved().collect {
                _marsPhotoSavedState.value = it
            }
        }
    }

    fun changeSavedStatus(roverPhotoUiModel: RoverPhotoUiModel) {
        viewModelScope.launch(ioDispatchers) {
            if (roverPhotoUiModel.isSaved) {
                marsRoverPhotoRepo.removePhoto(roverPhotoUiModel)
            } else {
                marsRoverPhotoRepo.savePhoto(roverPhotoUiModel = roverPhotoUiModel)
            }
        }
    }
}
