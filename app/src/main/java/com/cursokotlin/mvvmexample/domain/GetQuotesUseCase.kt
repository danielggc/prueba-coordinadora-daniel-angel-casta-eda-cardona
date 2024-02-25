package com.cursokotlin.mvvmexample.domain

import android.util.Log
import com.cursokotlin.mvvmexample.data.QuoteRepository
import com.cursokotlin.mvvmexample.data.database.entities.toDatabase
import com.cursokotlin.mvvmexample.domain.model.Remission
import java.lang.Exception
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {
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