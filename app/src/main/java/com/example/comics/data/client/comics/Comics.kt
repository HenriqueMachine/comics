package com.example.comics.data.client.comics

data class Comics(
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail
)

data class ComicsData(
    val data: ComicsList
)

data class ComicsList(
    val results: List<Comics>
)


