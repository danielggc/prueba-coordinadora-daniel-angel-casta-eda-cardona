package com.cursokotlin.mvvmexample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.cursokotlin.mvvmexample.data.QuoteRepository
import com.cursokotlin.mvvmexample.domain.GetQuotesUseCase
import com.cursokotlin.mvvmexample.domain.GetRandomQuoteUseCase
import com.cursokotlin.mvvmexample.domain.model.Quote
import com.cursokotlin.mvvmexample.domain.model.Remission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,

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

                getRandomQuoteUseCase.updateRemissionList(list)
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
            val response = getRandomQuoteUseCase.getRemissionsInBatches(pageSize, offset)
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