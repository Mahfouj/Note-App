package com.example.myreycycle

import androidx.room.*


@Dao
interface NoteDao{
         @Insert
        fun insetData(note: Note)

          @Update
         fun updateNoe(note: Note)

           @Delete
          fun deleteNote(note:Note)

          @Query("SELECT*From Note")
          fun getAllNote():List<Note>

    @Query("SELECT * FROM note WHERE noteid IN (:id)")
    fun  getByNoteId(id: List<Int>): List< Note>

}