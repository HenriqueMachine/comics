package com.example.comics.data.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comics.data.database.entity.ComicsEntity
import com.example.comics.data.database.dao.ComicsDAO

@Database(entities = [ComicsEntity::class], version = 1, exportSchema = false)
abstract class ComicsDatabase : RoomDatabase() {

    abstract fun createDAO() : ComicsDAO

}