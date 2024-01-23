package com.banklannister.marsrover.domain

sealed class RoverManifestUiState {
    data class Success(
        val roverManifestUiModelList: List<RoverManifestUiModel>
    ) : RoverManifestUiState()

    data object Loading : RoverManifestUiState()
    data object Error : RoverManifestUiState()
}

data class RoverManifestUiModel(
    val sol: String,
    val earthDate: String,
    val photoNumber: String
) : Comparable<RoverManifestUiModel> {

    override fun compareTo(other: RoverManifestUiModel): Int =
        other.earthDate.compareTo(this.earthDate)

}