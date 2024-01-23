package com.banklannister.marsrover.service.model

import com.google.gson.annotations.SerializedName

data class CameraRemoteModel(
    val id: Int,
    val name: String,

    @field: SerializedName("full_name")
    val fullName: String,

    @field: SerializedName("rover_id")
    val roverId: Int
)
