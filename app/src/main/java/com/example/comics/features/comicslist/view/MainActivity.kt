package com.example.comics.features.comicslist.view

import android.widget.Toast
import com.example.comics.R
import com.example.comics.application.BaseActivity
import com.example.comics.databinding.ActivityMainBinding
import com.example.comics.features.comicslist.adapter.ComicsAdapter
import com.example.comics.features.comicslist.model.ComicsViewObject
import com.example.comics.features.comicslist.state.ComicsViewState
import com.example.comics.features.comicslist.viewmodel.ComicsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ComicsViewState>() {

    private var binding: ActivityMainBinding? = null
    private var comicsAdapter: ComicsAdapter? = null

    override val viewModel: ComicsViewModel by viewModel()

    override fun setupScreen() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel.getComicsList()
        setupBindings()
    }

    override fun render(state: ComicsViewState) {
        when (state) {
            is ComicsViewState.ShowComicsList -> {
                setupAdapter(state.list)
            }

            is ComicsViewState.ShowError -> {
                //TODO: open dialog with error
            }

            is ComicsViewState.ShowComicsListFromDatabase -> {
                setupAdapter(state.list)
                Toast.makeText(this, getString(R.string.message_loaded_from_database), Toast.LENGTH_SHORT).show()
            }

            is ComicsViewState.ShowLoading -> {
                with(binding) {
                    this?.swipeRefresh?.isRefreshing = state.show
                }
            }
        }
    }

    private fun setupAdapter(list: List<ComicsViewObject>) {
        comicsAdapter = ComicsAdapter(list)
        comicsAdapter?.let { adapter ->
            binding?.listItem?.adapter = adapter
            adapter.onClickItem = {
                Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupBindings() = with(binding?.swipeRefresh) {
        this?.setOnRefreshListener {
            viewModel.getComicsList()
        }
    }

}