package com.example.final_mobile.data

import com.example.final_mobile.model.Events
import com.example.final_mobile.network.TicketApiService


interface TicketRepository {
    suspend fun getTickets(): List<Events>
}


class NetworkTicketRepository(
    private val ticketApiService: TicketApiService,
    private val typeString : String = "Concert"
) : TicketRepository {
    override suspend fun getTickets(): List<Events> = ticketApiService.getTickets(className = typeString).embedded.events
}