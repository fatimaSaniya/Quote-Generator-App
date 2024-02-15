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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp)
        )
        Text(
            text = "Your Fresh Quote✨!",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            modifier = Modifier.padding(top = 182.dp, start = 90.dp, end = 7.dp),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(250.dp),
                colors = CardDefaults.cardColors(containerColor = Color.hsl(10f, 0.36f, 0.81f))
            ) {
                Text(
                    text = quote,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 32.dp, start = 7.dp, end = 7.dp),
                     textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilledTonalButton(
                    onClick = { viewModel.getRandomQuote() },
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(Color.hsl(31f, 0.3f, 0.62f))
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
                    Text(text = " New Quote", color = Color.Black)
                }

                FilledTonalButton(
                    onClick = { shareQuote(context, quote) },
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(Color.hsl(31f, 0.3f, 0.62f))
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "share")
                    Text(" Share", color = Color.Black)
                }
            }
            Text(
                text = "Welcome!!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(top = 32.dp, start = 7.dp, end = 7.dp),
                textAlign = TextAlign.Center
            )
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
