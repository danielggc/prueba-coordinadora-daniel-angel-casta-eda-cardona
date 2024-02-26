package com.cursokotlin.mvvmexample.domain

import android.util.Log
import com.cursokotlin.mvvmexample.data.QuoteRepository
import com.cursokotlin.mvvmexample.domain.model.Remission
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {


    suspend fun getRemissionsInBatches( pageSize: Int, offset: Int ) :List<Remission>{
        Log.d("TAG", "getRemissionsInBatches: "+ pageSize )
        val list = repository.getRemissionsInBatchesFromDataBAse( pageSize, offset )
        Log.d("TAG", "getRemissionsInBatches: "+ list )

        if( !list.isNullOrEmpty() ){
            return list
        }
        //TODO aca deben de ir casos de errores
        return emptyList()
    }


    suspend fun updateRemissionList( remissionList: List<Remission> ){
        //TODO hacer validaciones aca
        val response = repository.actualizarRemisiones(remissionList)
        Log.d("TAG", "updateRemissionList: $response ")

    }


}