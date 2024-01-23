package com.banklannister.marsrover.service.model

import com.google.gson.annotations.SerializedName

data class PhotoRemoteModel(
    val camera: CameraRemoteModel,
    val id: Int,
    val rover: RoverRemoteModel,
    val sol: Int,

    @field: SerializedName("earth_date")
    val earthDate: String,

    @field: SerializedName("img_src")
    val imgSrc: String
)
