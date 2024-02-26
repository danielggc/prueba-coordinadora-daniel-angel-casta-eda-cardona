package com.shipping.test_cordinadora.data

import android.util.Log
import com.shipping.test_cordinadora.data.database.dao.RemissionDao
import com.shipping.test_cordinadora.data.database.entities.RemissionEntity
import com.shipping.test_cordinadora.data.database.entities.toDatabase
import com.shipping.test_cordinadora.data.model.ApiResponse
import com.shipping.test_cordinadora.data.network.QuoteService
import com.shipping.test_cordinadora.domain.model.Remission
import com.shipping.test_cordinadora.domain.model.toDomain
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemissionRepository @Inject constructor(
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