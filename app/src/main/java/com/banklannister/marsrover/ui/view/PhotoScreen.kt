package com.banklannister.marsrover.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.banklannister.marsrover.domain.RoverPhotoUiState
import com.banklannister.marsrover.ui.photolist.PhotoViewModel

@Composable
fun PhotoScreen(
    modifier: Modifier,
    roverName: String?,
    sol: String?,
    photoViewModel: PhotoViewModel
) {

    val viewState by photoViewModel.roverPhotoUiState.collectAsStateWithLifecycle()

    if (roverName != null && sol != null) {
        LaunchedEffect(Unit) {
            photoViewModel.getMarsRoverPhoto(roverName, sol)
        }

        when (val roverPhotoUiState = viewState) {
            RoverPhotoUiState.Error -> Error()
            RoverPhotoUiState.Loading -> Loading()
            is RoverPhotoUiState.Success -> PhotoList(
                modifier = modifier,
                roverPhotoUiModelList = roverPhotoUiState.roverPhotoUiModelList,
            ) { roverPhotoUiModel ->
                photoViewModel.changeSaveStatus(roverPhotoUiModel)
            }
        }
    }
}