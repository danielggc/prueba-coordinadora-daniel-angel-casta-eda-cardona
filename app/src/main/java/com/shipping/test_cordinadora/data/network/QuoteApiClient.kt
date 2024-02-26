package com.shipping.test_cordinadora.data.network

import com.shipping.test_cordinadora.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/prueba_tecnica/carga_trabajo/equipo/77/terminal/2/")
    suspend fun getAllQuotes(): Response<ApiResponse>
}