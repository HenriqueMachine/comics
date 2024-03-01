package com.example.comics.features.comicslist.model

import com.example.comics.data.client.comics.Comics

data class ComicsViewObject(
    val image: String,
    val title: String,
    val subtitle: String
) {

    companion object {
        fun map(list: List<Comics>): List<ComicsViewObject> {
            return list.map {
                ComicsViewObject(
                    image = "${it.thumbnail.path}.${it.thumbnail.extension}",
                    title = it.title,
                    subtitle = it.description ?: "None description"
                )
            }
        }
    }

}