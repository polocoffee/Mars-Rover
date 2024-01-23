package com.banklannister.marsrover.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.banklannister.marsrover.domain.RoverPhotoUiState
import com.banklannister.marsrover.ui.savedlist.SavedViewModel

@Composable
fun PhotoListSavedScreen(
    modifier: Modifier = Modifier,
    savedViewModel: SavedViewModel
) {
    val viewState by savedViewModel.marsPhotoSavedState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        savedViewModel.getAllSaved()
    }

    when (val roverPhotoUiState = viewState) {
        RoverPhotoUiState.Error -> Error()
        RoverPhotoUiState.Loading -> Loading()
        is RoverPhotoUiState.Success -> PhotoList(
            modifier = modifier,
            roverPhotoUiModelList = roverPhotoUiState.roverPhotoUiModelList,
            onClick = { roverPhotoUiModel ->
                savedViewModel.changeSavedStatus(roverPhotoUiModel)
            }
        )
    }
}