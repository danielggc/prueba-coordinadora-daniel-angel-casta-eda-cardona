package com.shipping.test_cordinadora.ui.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shipping.test_cordinadora.domain.model.Remission

class RemisionViewModel : ViewModel() {
    private val _remisionLiveData = MutableLiveData<Remission>()
    val remisionLiveData: LiveData<Remission> = _remisionLiveData

    fun enviarRemision(remision: Remission) {
        Log.d("Correct", "enviarRemision: ")
        _remisionLiveData.value = remision
    }
}