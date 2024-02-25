package com.cursokotlin.mvvmexample.data

import android.util.Log
import com.cursokotlin.mvvmexample.data.database.dao.RemissionDao
import com.cursokotlin.mvvmexample.data.database.entities.RemissionEntity
import com.cursokotlin.mvvmexample.data.model.ApiResponse
import com.cursokotlin.mvvmexample.data.network.QuoteService
import com.cursokotlin.mvvmexample.domain.model.Remission
import com.cursokotlin.mvvmexample.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: RemissionDao
) {

    suspend fun getAllQuotesFromApi(): List<Remission> {
        val response: ApiResponse = api.getQuotes()

        return response.data.map { e -> e.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Remission>{
        val response: List<RemissionEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun  getRemissionsInBatchesFromDataBAse( pageSize: Int, offset: Int ) : List<Remission>{
        val response :List<RemissionEntity> = quoteDao.getRemissionsInBatches( pageSize ,offset )
        Log.d("DATABASE", "getRemissionsInBatchesFromDataBAse:  " + response)
        return  response.map{it.toDomain()}
    }

    suspend fun insertQuotes(remissionModel:List<RemissionEntity>){
        Log.d("DATABASE", "insertQuotes:  " + remissionModel )

        quoteDao.insertAll(remissionModel)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}