package com.banklannister.marsrover.service.model

import com.google.gson.annotations.SerializedName

data class ManifestPhotoRemoteModel(
    val cameras: List<String>,
    val sol: Int,

    @field: SerializedName("earth_date")
    val earthDate: String,

    @field: SerializedName("total_photos")
    val totalPhotos: Int


)
