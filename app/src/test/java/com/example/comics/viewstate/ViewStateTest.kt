package com.example.comics.viewstate

import com.example.comics.features.comicslist.model.ComicsViewObject
import com.example.comics.features.comicslist.state.ComicsViewState
import org.junit.Assert.assertEquals
import org.junit.Test

class ComicsViewStateTest {

    @Test
    fun `test ShowComicsList state`() {
        val comicsList = listOf(
            ComicsViewObject("image1", "title1", "subtitle1"),
        )
        val state = ComicsViewState.ShowComicsList(comicsList)
        assertEquals(comicsList, state.list)
    }

    @Test
    fun `test ShowComicsListFromDatabase state`() {
        val comicsList = listOf(
            ComicsViewObject("imagebd1", "titlebd1", "subtitlebd1"),
        )
        val state = ComicsViewState.ShowComicsListFromDatabase(comicsList)
        assertEquals(comicsList, state.list)
    }

    @Test
    fun `test ShowLoading state`() {
        val state = ComicsViewState.ShowLoading(true)
        assertEquals(true, state.show)
        val stateFalse = ComicsViewState.ShowLoading(false)
        assertEquals(false, stateFalse.show)
    }
}
