package com.example.todonotes.notesFeature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.todonotes.R
import com.example.todonotes.notesFeature.domain.model.Note
import com.example.todonotes.notesFeature.presentation.components.LocalTheme
import com.example.todonotes.notesFeature.presentation.components.TopAppBarUI


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: UiState,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    onDelete: (Note) -> Unit,
    onToggleSelection: (Int) -> Unit,
    onClearSelection: () -> Unit,
    onDeleteSelected: () -> Unit,
    onSelectAll: () -> Unit
) {
    val theme = LocalTheme.current

    Scaffold(
        topBar = {
            if (uiState.isSelectedMode) {
                TopAppBar(
                    title = { Text("${uiState.selectedNotes.size} selected", color = theme.buttonColor) },
                    actions = {
                        IconButton(onClick = onSelectAll) {
                            Icon(
                                painter = painterResource(R.drawable.outline_select_all_24),
                                contentDescription = "Select All"
                            )
                        }
                        IconButton(onClick = onDeleteSelected) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_delete_24),
                                contentDescription = "Delete"
                            )
                        }
                        IconButton(onClick = onClearSelection) {
                            Icon(
                                painter = painterResource(R.drawable.outline_close_24),
                                contentDescription = "Cancel"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = theme.containerColor
                    )
                )
            } else {
                TopAppBarUI(title = "MainScreen")
            }
        },
        floatingActionButton = {
            if (!uiState.isSelectedMode) {
                FloatingActionButton(
                    onClick = onAddClick,
                    containerColor = theme.buttonColor
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_add_24),
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->

        if (uiState.data.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(theme.containerColor)
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_note_stack_add_24), // optional illustration
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        colorFilter = ColorFilter.tint(
                            theme.buttonColor
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No notes yet!",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tap + to add your first note",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }
        else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .background(theme.containerColor)
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(uiState.data) { note ->
                    val isSelected = note.id in uiState.selectedNotes
                    Card(
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 16.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 32.dp
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .combinedClickable(
                                onClick = {
                                    if (uiState.isSelectedMode) {
                                        onToggleSelection(note.id)
                                    } else {
                                        onEditClick(note.id)
                                    }
                                },
                                onLongClick = { onToggleSelection(note.id) }
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color.LightGray else theme.cardBackground
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = note.title,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = note.content,
                                    maxLines = 9,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            if (uiState.isSelectedMode) {
                                Checkbox(
                                    checked = isSelected,
                                    onCheckedChange = { onToggleSelection(note.id) }
                                )
                            } else {
                                IconButton(onClick = {}) {

                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
