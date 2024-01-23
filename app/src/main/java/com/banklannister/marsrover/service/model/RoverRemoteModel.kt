package com.banklannister.marsrover.service.model

import com.google.gson.annotations.SerializedName

data class RoverRemoteModel(
    val id: Int,
    val name: String,
    val status: String,

    @field: SerializedName("lading_date")
    val landingDate: String,

    @field: SerializedName("launch_date")
    val launchDate: String
)
