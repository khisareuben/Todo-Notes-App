package com.example.todonotes.notesFeature.domain

import com.example.todonotes.notesFeature.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    fun getAllNotes(): Flow<List<Note>>

}