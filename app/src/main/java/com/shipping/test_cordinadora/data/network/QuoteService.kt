package com.shipping.test_cordinadora.data.network

import android.util.Log
import com.shipping.test_cordinadora.data.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:QuoteApiClient) {

    suspend fun getQuotes(): ApiResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            Log.d("response ", "invoke: service " + response )

            response.body() ?: ApiResponse(false , emptyList() )
        }
    }

}