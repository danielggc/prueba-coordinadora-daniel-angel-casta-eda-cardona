package com.shipping.test_cordinadora.domain

import android.util.Log
import com.shipping.test_cordinadora.data.RemissionRepository
import com.shipping.test_cordinadora.data.database.entities.toDatabase
import com.shipping.test_cordinadora.domain.model.Remission
import java.lang.Exception
import javax.inject.Inject

class GetRemissionModelUseCase @Inject constructor(private val repository: RemissionRepository) {
    suspend operator fun invoke():List<Remission>{

        try {
            val quotes = repository.getAllQuotesFromApi()
            return if(quotes.isNotEmpty()){
                repository.clearQuotes()
                repository.insertQuotes( quotes.map {  it.toDatabase() })
                quotes
            }else{
                repository.getAllQuotesFromDatabase()
            }
        }
        catch ( e : Exception) {
            Log.d("ERROR",  " GetQuotesUseCase invoke: ${e.message}" )
            return repository.getAllQuotesFromDatabase()
        }


    }


}