package com.example.todonotes.notesFeature.presentation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable



@Composable
fun NotesApp(viewModel: MainViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Dest.MainScreen,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } }
    ) {
        composable<Dest.MainScreen> {
            MainScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onAddClick = { navController.navigate(Dest.AddNoteScreen) },
                onEditClick = { noteId -> navController.navigate(Dest.EditNoteScreen(noteId)) },
                onDelete = viewModel::delete,
                onToggleSelection = viewModel::toggleSelection,
                onClearSelection = viewModel::clearSelection,
                onDeleteSelected = viewModel::deleteSelected,
                onSelectAll = viewModel::selectAll
            )
        }
        composable<Dest.AddNoteScreen> {
            AddNoteScreen(
                onSave = {
                    viewModel.insert(it)
                    navController.popBackStack()
                }
            )
        }
        composable<Dest.EditNoteScreen> {
            val nodeId = it.toRoute<Dest.EditNoteScreen>().id
            val note = viewModel.uiState.collectAsState().value.data.find { it.id == nodeId }
            note?.let { it ->
                EditNoteScreen(
                    note = it,
                    onUpdate = {
                        viewModel.update(it)
                        navController.popBackStack()
                    }
                )
            }

        }
    }

}


sealed class Dest {
    @Serializable
    data object MainScreen: Dest()

    @Serializable
    data object AddNoteScreen: Dest()

    @Serializable
    data class EditNoteScreen(val id: Int): Dest()
}
