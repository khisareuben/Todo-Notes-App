package com.example.todonotes.notesFeature.mappers

import com.example.todonotes.notesFeature.data.local.NoteEntity
import com.example.todonotes.notesFeature.domain.model.Note

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(id, title, content)
}

fun NoteEntity.toNote(): Note {
    return Note(id, title, content)
}