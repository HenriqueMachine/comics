package com.example.comics.features.comicslist.state

import com.example.comics.features.comicslist.model.ComicsViewObject

sealed class ComicsViewState {
    data class ShowComicsList(val list: List<ComicsViewObject>) : ComicsViewState()
    data class ShowComicsListFromDatabase(val list: List<ComicsViewObject>) : ComicsViewState()
    data class ShowLoading (val show: Boolean) : ComicsViewState()
    object ShowError : ComicsViewState()
}