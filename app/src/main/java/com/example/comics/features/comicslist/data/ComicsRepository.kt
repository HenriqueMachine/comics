package com.example.comics.features.comicslist.data

import com.example.comics.BuildConfig
import com.example.comics.data.client.comics.ComicsAPI
import com.example.comics.data.client.comics.ComicsData
import com.example.comics.util.Response

class ComicsRepository (private val api : ComicsAPI) : ComicsService {
    override suspend fun getComicsList(): Response<ComicsData> {
        return try {
            val data = api.getComicsList(
                BuildConfig.API_TS,
                BuildConfig.API_KEY,
                BuildConfig.API_HASH
            )
            Response.Success(data)
        } catch (e : Exception){
            Response.Error(e)
        }
    }
}