package com.example.todonotes.notesFeature.data.repository

import com.example.todonotes.notesFeature.data.local.NoteDao
import com.example.todonotes.notesFeature.domain.NoteRepository
import com.example.todonotes.notesFeature.domain.model.Note
import com.example.todonotes.notesFeature.mappers.toNote
import com.example.todonotes.notesFeature.mappers.toNoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepoImpl(private val noteDao: NoteDao): NoteRepository {

    override suspend fun insert(note: Note) {
        noteDao.insert(note.toNoteEntity())
    }

    override suspend fun update(note: Note) {
        noteDao.update(note.toNoteEntity())
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note.toNoteEntity())
    }

    override fun getAllNotes(): Flow<List<Note>> =
        noteDao.getAllNotes().map { it.map { it.toNote() } }
}