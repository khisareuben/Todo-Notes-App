package com.example.todonotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todonotes.notesFeature.presentation.NotesApp
import com.example.todonotes.notesFeature.presentation.components.LocalTheme
import com.example.todonotes.notesFeature.presentation.components.darkThemeColors
import com.example.todonotes.notesFeature.presentation.components.lightThemeColor
import com.example.todonotes.ui.theme.TodoNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoNotesTheme {
                val theme = if (isSystemInDarkTheme()) darkThemeColors else lightThemeColor
                CompositionLocalProvider(LocalTheme provides theme) {
                    NotesApp()
                }

            }
        }
    }
}

