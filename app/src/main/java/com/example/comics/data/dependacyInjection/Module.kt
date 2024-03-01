package com.example.comics.data.dependacyInjection

import androidx.room.Room
import com.example.comics.BuildConfig
import com.example.comics.data.client.RetrofitClient
import com.example.comics.data.client.comics.ComicsAPI
import com.example.comics.data.database.database.ComicsDatabase
import com.example.comics.features.comicslist.data.ComicsRepository
import com.example.comics.features.comicslist.data.ComicsService
import com.example.comics.features.comicslist.viewmodel.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        RetrofitClient().createWebService<ComicsAPI>(
            okHttpClient = RetrofitClient().createHttpClient(),
            baseUrl = BuildConfig.BASE_COMICS_PATH
        )
    }

    single {
        Room.databaseBuilder(
            get(),
            ComicsDatabase::class.java,
            "database.db"
        )
            .build()
    }

    single { get<ComicsDatabase>().createDAO() }
    factory<ComicsService> { ComicsRepository(api = get()) }
    viewModel { ComicsViewModel(service = get(), database = get()) }

}