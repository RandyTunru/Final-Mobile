package com.example.final_mobile.ui.display

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.final_mobile.TicketsApplication
import com.example.final_mobile.data.TicketRepository
import com.example.final_mobile.model.Events
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface TicketState {
    data class Success(val events: List<Events>) : TicketState
    object Error : TicketState
    object Loading : TicketState
}

class TicketsDisplayViewModel(private val ticketRepository: TicketRepository) : ViewModel() {
    var ticketState: TicketState by mutableStateOf(TicketState.Loading)
        private set


    init {
        getTickets()
    }


    fun getTickets() {
        viewModelScope.launch {
            ticketState = TicketState.Loading
            ticketState = try {
                TicketState.Success(ticketRepository.getTickets())
            } catch (e: IOException) {
                TicketState.Error
            } catch (e: HttpException) {
                TicketState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TicketsApplication)
                val ticketRepository = application.container.ticketRepository
                TicketsDisplayViewModel(ticketRepository = ticketRepository)
            }
        }
    }
}