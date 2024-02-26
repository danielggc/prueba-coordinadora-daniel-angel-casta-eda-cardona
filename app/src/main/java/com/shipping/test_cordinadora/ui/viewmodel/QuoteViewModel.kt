package com.shipping.test_cordinadora.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.shipping.test_cordinadora.domain.GetRemissionModelUseCase
import com.shipping.test_cordinadora.domain.GetRandomRemissionUseCase
import com.shipping.test_cordinadora.domain.model.Remission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetRemissionModelUseCase,
    private val getRandomRemissionUseCase: GetRandomRemissionUseCase,

    ) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = false }



    fun updateRemissionList(remissionList: List<Remission>) {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val list: List<Remission> = remissionList.mapIndexedNotNull { index, remission ->
                    if (remission.order != index) {
                        remission.copy(order = index)
                    } else {
                        null
                    }
                }

                getRandomRemissionUseCase.updateRemissionList(list)
                Log.d("SAVEDATA" , "here my data ${remissionList}")
                isLoading.postValue(false)

            } catch (e: Exception) {
                Log.e("Error", "Error al actualizar la lista de remisiones: ${e.message}")
                isLoading.postValue(false)

            }
        }
    }
    fun getRemissionsInBatches(pageSize: Int, offset: Int): LiveData<List<Remission>> {
        Log.d("TAG", "getRemissionsInBatches: "+  offset)
        return liveData{
            val response = getRandomRemissionUseCase.getRemissionsInBatches(pageSize, offset)
            emit(response)
        }
    }




    fun onCreate() {
        isLoading.postValue(true)
        viewModelScope.launch {
            val result = getQuotesUseCase()
            Log.d("sabeDataBAse ", "invoke: " + result)
            isLoading.postValue(false)

        }
    }




}