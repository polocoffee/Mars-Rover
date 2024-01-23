package com.banklannister.marsrover.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.banklannister.marsrover.domain.RoverManifestUiState
import com.banklannister.marsrover.ui.manifestlist.ManifestViewModel

@Composable
fun ManifestScreen(
    modifier: Modifier,
    roverName: String?,
    manifestViewModel: ManifestViewModel,
    onClick: (roverName: String, sol: String) -> Unit
) {
    val viewState by manifestViewModel.roverManifestUiState.collectAsStateWithLifecycle()

    if (roverName != null) {
        LaunchedEffect(Unit) {
            manifestViewModel.getMarsRoverManifest(roverName)
        }

        when (val roverManifestUiState = viewState) {
            RoverManifestUiState.Error -> Error()
            RoverManifestUiState.Loading -> Loading()
            is RoverManifestUiState.Success -> ManifestList(
                modifier = modifier,
                roverManifestUiModelList = roverManifestUiState.roverManifestUiModelList,
                roverName = roverName,
                onClick = onClick
            )
        }
    }
}