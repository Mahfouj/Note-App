package com.example.myreycycle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

   @Database(entities = [Note::class ], version = 1)
abstract class NoteDatabase:RoomDatabase() {
       abstract fun getNoteDao(): NoteDao

       companion object {

        private   var database: NoteDatabase? = null

           fun getDB(context: Context): NoteDatabase {

               if (database == null) {
                   database =
                       Room.databaseBuilder(
                           context,
                           NoteDatabase::class.java,
                           "Note-db "
                       ).allowMainThreadQueries()
                           .build()

                   return database as NoteDatabase


               } else {


                   return database as NoteDatabase


               }


           }
       }


   }
