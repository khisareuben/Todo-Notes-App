package com.example.todonotes.notesFeature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todonotes.R
import com.example.todonotes.notesFeature.domain.model.Note
import com.example.todonotes.notesFeature.presentation.components.LocalTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(onSave: (Note) -> Unit) {

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val theme = LocalTheme.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add Note",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (title.isNotBlank() && content.isNotBlank()) {
                            onSave(Note(id = 0, title = title, content = content))
                        }
                    }) {
                        Image(
                            painter = painterResource(R.drawable.outline_check_circle_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                theme.buttonColor
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = theme.containerColor
                )
            )
        }
    ) { paddingValues ->

        Column(
            Modifier.background(theme.containerColor).padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    cursorColor = theme.buttonColor,
                    focusedContainerColor = theme.containerColor,
                    unfocusedContainerColor = theme.containerColor,
                    focusedIndicatorColor = theme.buttonColor

                )
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(55.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${content.length} characters")
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                value = content,
                onValueChange = { content = it },
                placeholder = {Text("Start typing", color = Color.Gray)},
                label = { Text("Content") },
                colors = TextFieldDefaults.colors(
                    cursorColor = theme.buttonColor,
                    focusedContainerColor = theme.containerColor,
                    unfocusedContainerColor = theme.containerColor,
                    focusedIndicatorColor = theme.buttonColor

                )

            )

    }


    }

}