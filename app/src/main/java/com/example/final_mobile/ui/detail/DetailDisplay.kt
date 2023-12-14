package com.example.final_mobile.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.final_mobile.MainActivity
import com.example.final_mobile.R
import com.example.final_mobile.model.Events
import com.example.final_mobile.model.Url
import com.example.final_mobile.ui.theme.FinalmobileTheme

class DetailDisplay : ComponentActivity() {
    private var selectedEvents: Events? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        selectedEvents = intent.getParcelableExtra("EVENT")
        super.onCreate(savedInstanceState)
        setContent {
            FinalmobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailDisplayContent(event = selectedEvents!!)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DetailDisplayContent(event: Events) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = event.name)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                modifier = Modifier.background(Color.Green)
            )
        },
        content = { paddingValues ->
            val padding = paddingValues
            DetailDisplayBody(event = event, modifier = Modifier)
        }
    )
}

@Composable
fun DetailDisplayBody(event: Events, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Display event name and description
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(event.images[0].url)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = "Blank",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column {
                Text(
                    text = event.name,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = event.description,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

    }
}

