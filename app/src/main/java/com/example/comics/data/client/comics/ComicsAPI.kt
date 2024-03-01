package com.example.comics.data.client.comics

import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsAPI {

    @GET(ComicsURL.GET_COMICS_LIST)
    suspend fun getComicsList(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : ComicsData

}