package com.banklannister.marsrover.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Suppress("AndroidUnresolvedRoomSqlReference")
@Dao
interface MarsRoverSavedPhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marsRoverSavedLocalModel: MarsRoverSavedLocalModel)

    @Delete
    suspend fun delete(marsRoverSavedLocalModel: MarsRoverSavedLocalModel)

    @Query("SELECT roverPhotoId FROM rover_photo WHERE sol =:sol AND roverName = :roverName")
    fun allSavedId(sol: String, roverName: String): Flow<List<Int>>

    @Query("SELECT * FROM rover_photo ORDER BY earthDate DESC")
    fun getAllSaved(): Flow<List<MarsRoverSavedLocalModel>>
}
