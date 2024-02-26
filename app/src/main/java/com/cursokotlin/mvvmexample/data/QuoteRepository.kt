package com.cursokotlin.mvvmexample.data

import android.util.Log
import com.cursokotlin.mvvmexample.data.database.dao.RemissionDao
import com.cursokotlin.mvvmexample.data.database.entities.RemissionEntity
import com.cursokotlin.mvvmexample.data.database.entities.toDatabase
import com.cursokotlin.mvvmexample.data.model.ApiResponse
import com.cursokotlin.mvvmexample.data.network.QuoteService
import com.cursokotlin.mvvmexample.domain.model.Remission
import com.cursokotlin.mvvmexample.domain.model.toDomain
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: RemissionDao
) {

    suspend fun getAllQuotesFromApi(): List<Remission> {
        val response: ApiResponse = api.getQuotes()

        return  response.data.mapIndexed { index, e -> e.toDomain(index) }
    }

    suspend fun getAllQuotesFromDatabase():List<Remission>{
        val response: List<RemissionEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }


    suspend fun actualizarRemisiones(remissionList: List<Remission>):Int{
        val updatedRows = withContext(Dispatchers.IO) {
            quoteDao.updateOrder(remissionList.map { it.toDatabase() })
        }
        return updatedRows
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