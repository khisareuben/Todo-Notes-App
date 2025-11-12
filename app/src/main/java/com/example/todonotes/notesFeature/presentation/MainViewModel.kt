package com.example.todonotes.notesFeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todonotes.notesFeature.domain.model.Note
import com.example.todonotes.notesFeature.domain.useCases.DeleteUseCase
import com.example.todonotes.notesFeature.domain.useCases.GetAllNotesUseCase
import com.example.todonotes.notesFeature.domain.useCases.InsertUseCase
import com.example.todonotes.notesFeature.domain.useCases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val updateUseCase: UpdateUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    /*val uiState = getAllNotesUseCase.invoke()
        .map { UiState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState())*/

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllNotesUseCase.invoke().collect { notes ->
                _uiState.update { it.copy(data = notes) }
            }
        }
    }

    fun insert(note: Note) = viewModelScope.launch {
        insertUseCase.invoke(note)
    }
    fun update(note: Note) = viewModelScope.launch {
        updateUseCase.invoke(note)
    }
    fun delete(note: Note) = viewModelScope.launch {
        deleteUseCase.invoke(note)
    }


    fun toggleSelection(noteId: Int) {
        _uiState.update { current ->
            val newSelection = if (noteId in current.selectedNotes) {
                current.selectedNotes - noteId
            } else {
                current.selectedNotes + noteId
            }
            current.copy(
                selectedNotes = newSelection,
                isSelectedMode = newSelection.isNotEmpty()
            )
        }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedNotes = emptySet(), isSelectedMode = false) }
    }

    fun deleteSelected() = viewModelScope.launch {
        val selectedIds = _uiState.value.selectedNotes
        _uiState.value.data.filter { it.id in selectedIds }.forEach { deleteUseCase.invoke(it) }
        clearSelection()
    }

    fun selectAll() {
        val allIds = _uiState.value.data.map { it.id }.toSet()
        _uiState.update { it.copy(selectedNotes = allIds, isSelectedMode = allIds.isNotEmpty()) }
    }


}

data class UiState(
    val data: List<Note> = emptyList(),
    val selectedNotes: Set<Int> = emptySet(),
    val isSelectedMode: Boolean = false
)