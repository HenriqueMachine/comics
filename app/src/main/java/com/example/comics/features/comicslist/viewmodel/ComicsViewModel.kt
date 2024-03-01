package com.example.comics.features.comicslist.viewmodel

import android.util.Log
import com.example.comics.application.SingleStateViewModel
import com.example.comics.data.database.database.ComicsDatabase
import com.example.comics.data.database.entity.ComicsEntity
import com.example.comics.features.comicslist.model.ComicsViewObject
import com.example.comics.features.comicslist.data.ComicsService
import com.example.comics.features.comicslist.state.ComicsViewState
import com.example.comics.util.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ComicsViewModel(
    private val service: ComicsService,
    private val database: ComicsDatabase
) : SingleStateViewModel<ComicsViewState>(), CoroutineScope {

    companion object {
        const val ClassNameLog = "ComicsViewModel"
    }

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    fun getComicsList() {
        launch {
            emit(ComicsViewState.ShowLoading(true))
            val request = withContext(Dispatchers.IO) {
                service.getComicsList()
            }
            when (request) {
                is Response.Success -> {
                    emit(ComicsViewState.ShowLoading(false))
                    database.createDAO().deleteComics()
                    val listComicsMapped =
                        ComicsViewObject.map(request.value.data.results)
                    ComicsEntity.mapEntity(listComicsMapped).forEach {
                        database.createDAO().insertComics(it)
                    }
                    emit(ComicsViewState.ShowComicsList(listComicsMapped))
                }

                is Response.Error -> {
                    Log.e(ClassNameLog, request.exception.toString())
                    emit(ComicsViewState.ShowLoading(false))
                    database.createDAO().getCachedComics().run {
                        if (this.isNotEmpty()) {
                            emit(
                                ComicsViewState.ShowComicsListFromDatabase(
                                    ComicsEntity.mapViewObject(
                                        this
                                    )
                                )
                            )
                        } else {
                            emit(ComicsViewState.ShowError)
                        }
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}