package com.example.quotegenerator.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quotegenerator.R
import com.example.quotegenerator.ui.theme.QuoteViewModel

@Composable
fun QuoteScreen(viewModel: QuoteViewModel, context: Context) {
    val quote by viewModel.currentQuote.observeAsState("")
    Box {
        Image(
            painter = painterResource(id = R.drawable.bg11),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(250.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.hsl(10f, 0.36f, 0.81f)
                )
            ) {
            Text(
                quote,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, top = 52.dp, end = 9.dp)
            )
        }

            Spacer(modifier = Modifier.height(36.dp))

            Row {
                FilledTonalButton(
                    onClick = { viewModel.getRandomQuote() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
                    Text(text = "Get new Quote")
                }

                FilledTonalButton(
                    onClick = { shareQuote(context, Typography.quote.toString()) },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "share")
                    Text("Share quote")
                }
            }
        }
    }
}

fun shareQuote(context: Context, quote: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, quote)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
