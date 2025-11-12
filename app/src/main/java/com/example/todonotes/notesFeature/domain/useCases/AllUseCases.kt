package com.example.todonotes.notesFeature.domain.useCases

import com.example.todonotes.notesFeature.domain.NoteRepository
import com.example.todonotes.notesFeature.domain.model.Note
import javax.inject.Inject

class InsertUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.insert(note)

}

class UpdateUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.update(note)

}

class DeleteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.delete(note)

}

class GetAllNotesUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    operator fun invoke() = noteRepository.getAllNotes()

}