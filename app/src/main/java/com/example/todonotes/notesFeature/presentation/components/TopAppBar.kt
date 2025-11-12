package com.example.todonotes.notesFeature.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todonotes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(
    title: String,
) {

    val theme = LocalTheme.current

    TopAppBar(
        modifier = Modifier.padding(bottom = 8.dp),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = theme.buttonColor,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = theme.containerColor
        )
    )

}



/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(
    title: String,
    selectionMode: Boolean,
    selectedCount: Int,
    totalCount: Int,
    onDeleteSelected: () -> Unit,
    onCancelSelection: () -> Unit,
    onSelectAll: () -> Unit
) {

    val theme = LocalTheme.current

    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(targetState = selectionMode, label = "Notes") { mode ->
                    if (mode) {
                        Text(
                            text = "$selectedCount selected",
                            color = theme.buttonColor,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = "Notes",
                            color = theme.buttonColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        actions = {
            if (selectionMode) {
                if (selectedCount < totalCount) {
                    IconButton(onClick = onSelectAll) {
                        Image(
                            painter = painterResource(R.drawable.outline_select_all_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                theme.buttonColor
                            )
                        )
                    }
                    IconButton(onClick = onDeleteSelected) {
                        Image(
                            painter = painterResource(R.drawable.baseline_delete_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                theme.buttonColor
                            )
                        )
                    }
                    IconButton(onClick = onCancelSelection) {
                        Image(
                            painter = painterResource(R.drawable.outline_close_24),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                theme.buttonColor
                            )
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = theme.containerColor
        )
    )

}*/
