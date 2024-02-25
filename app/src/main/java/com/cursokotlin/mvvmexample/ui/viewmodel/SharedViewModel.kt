package com.cursokotlin.mvvmexample.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursokotlin.mvvmexample.domain.model.Remission

class SharedViewModel : ViewModel() {
    private val _deliveryInfo = MutableLiveData<MutableList<Remission>>()
    val deliveryInfo: LiveData<MutableList<Remission>> = _deliveryInfo

    fun addRemission(remission: Remission) {
        val currentList = _deliveryInfo.value ?: mutableListOf()
        currentList.add(remission)
        _deliveryInfo.value = currentList
    }

    fun deleteAllRemissions() {
        _deliveryInfo.value?.clear()
        _deliveryInfo.value = _deliveryInfo.value // Actualiza el LiveData para notificar a los observadores
    }
}