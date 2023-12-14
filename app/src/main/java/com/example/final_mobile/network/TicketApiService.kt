package com.example.final_mobile.network

import com.example.final_mobile.model.EventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketApiService {
    @GET("events")
    suspend fun getTickets(
        @Query("apikey") apiKey : String = "yNWJpKN0jf1oyoAAIRXYG7mFXZmkvAbf",
        @Query("classificationName") className : String
    ): EventResponse

}