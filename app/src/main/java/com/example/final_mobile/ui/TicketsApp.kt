@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.final_mobile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_mobile.ui.display.TicketsDisplay
import com.example.final_mobile.ui.display.TicketsDisplayViewModel

@Composable
fun TicketsApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val ticketDisplayViewModel: TicketsDisplayViewModel =
            viewModel(factory = TicketsDisplayViewModel.Factory)

        Column (modifier = Modifier){
            Text(text = "Ticketing App",
                fontSize = 36.sp,)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Discover Various Tickets")
            TicketsDisplay(
                ticketState = ticketDisplayViewModel.ticketState,
                retryAction = ticketDisplayViewModel::getTickets)
        }
    }
}

@Preview
@Composable
fun PreviewTicketsApp(){
    TicketsApp()
}

