package com.example.comics.application

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.comics.databinding.ActivityMainBinding

abstract class BaseActivity<S : Any> : AppCompatActivity() {

    abstract val viewModel: SingleStateViewModel<S>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel) { logState(it); render(it) }
        setupScreen()
    }

    abstract fun render(state: S)

    abstract fun setupScreen()

    open fun fetch(){}

    open fun configureBindings(){}

    private fun logState(it: S) {
        Log.d("MVI", ">>> Rendering: ${it::class.java}")
    }

    private fun observe(viewModel: SingleStateViewModel<S>, block: (S) -> Unit) {
        viewModel.state.asLiveData().observe(this, EventObserver(block))
    }

}