package com.banklannister.marsrover.domain

sealed class RoverPhotoUiState {
    data class Success(
        val roverPhotoUiModelList: List<RoverPhotoUiModel>
    ): RoverPhotoUiState()

    data object Loading: RoverPhotoUiState()
    data object Error: RoverPhotoUiState()

}


data class RoverPhotoUiModel(
    val id: Int,
    val roverName: String,
    val imgSrc: String,
    val sol: String,
    val earthDate: String,
    val cameraFullName: String,
    val isSaved: Boolean
)