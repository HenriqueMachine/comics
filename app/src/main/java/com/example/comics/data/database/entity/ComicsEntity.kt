package com.example.comics.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comics.data.client.comics.Thumbnail
import com.example.comics.features.comicslist.model.ComicsViewObject
import java.io.Serializable
import kotlin.random.Random

@Entity(tableName = "comicsTable")
data class ComicsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val thumbnailPath: String
) : Serializable {
    companion object {
        fun mapEntity(list: List<ComicsViewObject>): List<ComicsEntity> {
            return list.map {
                ComicsEntity(
                    title = it.title,
                    description = it.subtitle,
                    thumbnailPath = it.image,
                    id = Random.nextInt()
                )
            }
        }

        fun mapViewObject(list: List<ComicsEntity>): List<ComicsViewObject> {
            return list.map {
                ComicsViewObject(
                    title = it.title,
                    subtitle = it.description,
                    image = it.thumbnailPath,
                )
            }
        }
    }
}