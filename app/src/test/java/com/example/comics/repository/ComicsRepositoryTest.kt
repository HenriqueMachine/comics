package com.example.comics.repository

import com.example.comics.data.client.comics.Comics
import com.example.comics.data.client.comics.ComicsAPI
import com.example.comics.data.client.comics.ComicsData
import com.example.comics.data.client.comics.ComicsList
import com.example.comics.data.client.comics.Thumbnail
import com.example.comics.features.comicslist.data.ComicsRepository
import com.example.comics.util.Response
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception

class ComicsRepositoryTest {

    @Test
    fun `test getComicsList success`() {
        val api = mockk<ComicsAPI>()
        val comicsData = ComicsData(ComicsList(listOf(Comics("", "", Thumbnail("", "")))))
        coEvery { api.getComicsList(any(), any(), any()) } returns comicsData

        val repository = ComicsRepository(api)
        val result = runBlocking { repository.getComicsList() }

        assertEquals(comicsData, (result as Response.Success).value)
    }

    @Test
    fun `test getComicsList error`() {
        val api = mockk<ComicsAPI>()
        val exception = Exception("Mocked exception")
        coEvery { api.getComicsList(any(), any(), any()) } throws exception

        val repository = ComicsRepository(api)

        val result = runBlocking { repository.getComicsList() }

        assertEquals((result as Response.Error).exception , Response.Error(exception).exception)
    }
}
