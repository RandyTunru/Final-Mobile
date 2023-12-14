package com.example.final_mobile.ui.display

import android.content.Intent
import android.media.metrics.Event
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.final_mobile.R
import com.example.final_mobile.model.Events
import com.example.final_mobile.ui.detail.DetailDisplay

@Composable
fun TicketsDisplay(
    ticketState: TicketState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (ticketState) {
        is TicketState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is TicketState.Success -> TicketLine(events = ticketState.events, modifier = modifier.fillMaxWidth())
        is TicketState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun ticketCard(event: Events, modifier: Modifier = Modifier){
    val context = LocalContext.current
    val density = LocalDensity.current.density

    Card(
        modifier = modifier.clickable {
            val intent = Intent(context, DetailDisplay::class.java)
            intent.putExtra("EVENT", event)
            context.startActivity(intent)
        },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // AsyncImage with weight
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(event.images[0].url)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "Blank",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f) // Take 1 part of the available height
                    .fillMaxHeight()
            )

            // Text elements with weight
            Column(
                modifier = Modifier
                    .weight(1f) // Take 1 part of the available height
                    .padding(8.dp)
            ) {
                Text(text = event.name, fontWeight = FontWeight.Bold)

                // Adjust the maxLines parameter based on your needs

                Text(
                    text = event.description,
                    maxLines = 2, // Adjust as needed
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TicketLine(events: List<Events>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(events) { event ->
            ticketCard(
                event,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}