package com.example.todonotes.notesFeature.di

import android.content.Context
import androidx.room.Room
import com.example.todonotes.notesFeature.data.local.NoteDao
import com.example.todonotes.notesFeature.data.local.NoteDatabase
import com.example.todonotes.notesFeature.data.repository.NoteRepoImpl
import com.example.todonotes.notesFeature.domain.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "Note.db"
        ).build()
    }

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.getNoteDao()
    }

    @Provides
    fun provideRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepoImpl(noteDao)
    }

}