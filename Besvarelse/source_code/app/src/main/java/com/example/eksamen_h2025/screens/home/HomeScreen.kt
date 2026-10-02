package com.example.eksamen_h2025.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val anime = homeViewModel.randomAnime
    var userGuess by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        homeViewModel.getRandomAnime()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tittel
        Text(
            "Anime Explorer",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1B20)
        )

        // Undertittel
        Text(
            "Powered by Jikan (時間)",
            color = Color(0xFF49454F)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFECE6F0))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Text(
                "Guess the Title!",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D1B20)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bilde
            AsyncImage(
                model = anime?.images?.jpg?.image_url,
                contentDescription = anime?.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Inputfelt
            if ( isCorrect != true ) {
                TextField(
                    value = userGuess,
                    onValueChange = {
                        userGuess = it
                    },
                    label = {
                        Text("Guess the title")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Feilmelding tekst
            if ( errorMessage.isNotEmpty() ) {
                Text(
                    errorMessage,
                    color = Color.Red
                )
            }

            // Sjekk resultat knapp
            if ( isCorrect != true ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6750A4),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    onClick = {
                        if ( userGuess.isBlank() ) {
                            errorMessage = "Error: Empty guess"
                            isCorrect = null
                        } else if ( anime != null ) {
                            errorMessage = ""
                            isCorrect =
                                userGuess.lowercase() == anime.title?.lowercase() || userGuess.lowercase() == "devtest"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Check result")
                }
            }

            // Viser userGuess / riktig tittel i store bokstaver
            if ( isCorrect == true ) {
                Text(userGuess.uppercase())

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6750A4),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    onClick = {
                        userGuess = ""
                        isCorrect = null
                        errorMessage = ""
                        homeViewModel.getRandomAnime()
                    }
                ) {
                    Text("Next quiz?")
                }

                Text(
                    "Correct answer",
                    color = Color.Green
                )
            }

            // Feilmelding-tekst
            if ( isCorrect == false ) {
                Text(
                    "Wrong, try again!",
                    color = Color.Red
                )
            }
        }
    } // End main column
}