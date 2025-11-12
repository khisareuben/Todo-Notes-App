package com.example.todonotes.notesFeature.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val notes_table = "notes"

@Entity(notes_table)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("content") val content: String
) {
    constructor(): this(0, "", "")
}
