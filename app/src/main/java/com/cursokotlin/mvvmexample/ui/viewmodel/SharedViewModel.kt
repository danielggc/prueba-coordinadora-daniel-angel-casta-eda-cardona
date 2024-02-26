package com.cursokotlin.mvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MyViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _shouldUpdate = MutableLiveData<Boolean>().apply { value = false }
    val shouldUpdate: LiveData<Boolean> = _shouldUpdate

    init {
        _isLoading.value = false
        _shouldUpdate.value = false
    }

    fun update() {
        // Lógica de actualización aquí
    }

    fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    fun setShouldUpdate(update: Boolean) {
        _shouldUpdate.value = update
    }
}