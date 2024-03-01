package com.example.comics.features.comicslist.data

import com.example.comics.data.client.comics.ComicsData
import com.example.comics.util.Response

interface ComicsService {
    suspend fun getComicsList() : Response<ComicsData>
}