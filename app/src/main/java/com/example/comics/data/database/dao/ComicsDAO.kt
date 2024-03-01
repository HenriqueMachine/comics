package com.example.comics.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comics.data.database.entity.ComicsEntity

@Dao
interface ComicsDAO {
    @Query("SELECT * FROM comicsTable")
    suspend fun getCachedComics(): List<ComicsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(comics: ComicsEntity)

    @Query("DELETE FROM comicsTable")
    suspend fun deleteComics()
}