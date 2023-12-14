package com.example.final_mobile.data

import com.example.final_mobile.model.EventResponse
import com.example.final_mobile.network.TicketApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

interface AppContainer {
    val ticketRepository: TicketRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://app.ticketmaster.com/discovery/v2/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */


    // Create a converter factory with the custom JSON instance
    class EventResponseConverter(
        private val json: Json
    ) : Converter<ResponseBody, EventResponse> {

        override fun convert(value: ResponseBody): EventResponse? {
            // Deserialize the JSON response using kotlinx.serialization
            return json.decodeFromString(EventResponse.serializer(), value.string())
        }

        companion object {
            fun create(): EventResponseConverter {
                // Create a Json instance with your desired configuration
                val json = Json {
                    // Add any serialization configuration options here if needed
                    ignoreUnknownKeys = true
                }

                return EventResponseConverter(json)
            }
        }
    }

    val converterFactory: Converter.Factory = Json {
        ignoreUnknownKeys = true
    }.asConverterFactory("application/json".toMediaType())

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: TicketApiService by lazy {
        retrofit.create(TicketApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val ticketRepository: TicketRepository by lazy {
        NetworkTicketRepository(retrofitService)
    }
}