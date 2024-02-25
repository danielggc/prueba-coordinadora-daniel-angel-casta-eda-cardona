package com.cursokotlin.mvvmexample.data.network

import com.cursokotlin.mvvmexample.data.model.ApiResponse
import com.cursokotlin.mvvmexample.data.model.RemissionModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/prueba_tecnica/carga_trabajo/equipo/77/terminal/2/")
    suspend fun getAllQuotes(): Response<ApiResponse>
}