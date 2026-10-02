package com.example.eksamen_h2025.screens.animeedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamen_h2025.R

@Composable
fun AnimeEditScreen(
    viewModel: AnimeEditViewModel,
    navController: NavController,
    animeId: Int
) {
    val anime by viewModel.anime.collectAsState()

    LaunchedEffect(animeId) {
        viewModel.loadAnime(animeId)
    }

    var isDeleted by remember { mutableStateOf(false) }

    if (anime == null && !isDeleted) {
        Text("Something went wrong. Could not find Anime")
    }

    anime?.let { animeToEdit ->
        var title by remember(animeToEdit) { mutableStateOf(animeToEdit.title) }
        var genre by remember(animeToEdit) { mutableStateOf(animeToEdit.genre) }
        var description by remember(animeToEdit) { mutableStateOf(animeToEdit.description) }
        var errorMessage by remember { mutableStateOf("") }
        var saveMessage by remember { mutableStateOf("") }

        val scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tilbakeknapp
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_circle_left),
                        contentDescription = "Tilbakeknapp",
                        tint = Color(0xFF1D1B20),
                        modifier = Modifier.size(40.dp)
                    )
                }

                // Tittel
                Text(
                    "Edit Anime",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 40.dp),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF1D1B20)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {

                // Informasjon tekst
                Text(
                    "Here you can edit or delete your Anime",
                    color = Color(0xFF49454F)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tittel-felt
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = {
                        if ( it.length <= 35 ) {
                            title = it
                        }
                    },
                    label = {
                        Text("Title")
                    }
                )
                Text(
                    "${title.length}/35",
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Sjanger-felt
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = genre,
                    onValueChange = {
                        if ( it.length <= 25 ) {
                            genre = it
                        }
                    },
                    label = {
                        Text("Genre")
                    }
                )

                Text(
                    "${genre.length}/25",
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Beskrivelse-felt
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    value = description,
                    onValueChange = {
                        if ( it.length <= 2000 ) {
                            description = it
                        }
                    },
                    label = {
                        Text("Description")
                    }
                )

                Text(
                    "${description.length}/2000",
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Feilmelding
                if ( errorMessage != "" ) {
                    Text(
                        errorMessage,
                        color = Color.Red
                    )
                }

                // Informasjonsmelding
                if ( saveMessage != "" ) {
                    Text(
                        saveMessage,
                        color = Color.Green
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    // Lagre-knapp
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6750A4),
                            contentColor = Color(0xFFFFFFFF)
                        ),
                        onClick = {
                            if ( title.isBlank() || genre.isBlank() || description.isBlank() ) {
                                errorMessage = "Error: You cannot leave one or more field empty"
                                saveMessage = ""
                            } else if ( title == animeToEdit.title && genre == animeToEdit.genre
                                && description == animeToEdit.description
                            ) {

                                errorMessage = "Error: No changes detected"
                                saveMessage = ""
                            } else {
                                val updated = animeToEdit.copy(
                                    title = title,
                                    genre = genre,
                                    description = description
                                )
                                viewModel.updateAnime(updated)

                                errorMessage = ""
                                saveMessage = "Success: Changes has been saved"
                            }
                        }
                    ) {
                        Text("Save changes")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Slett-knapp
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF9DEDC),
                            contentColor = Color(0xFF8C1D18)
                        ),
                        onClick = {
                            isDeleted = true
                            viewModel.deleteAnime(animeToEdit)
                            navController.popBackStack()
                        }
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}