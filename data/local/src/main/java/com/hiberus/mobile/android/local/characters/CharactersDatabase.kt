package com.hiberus.mobile.android.local.characters

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hiberus.mobile.android.local.characters.dbo.*
import com.hiberus.mobile.android.local.converter.DateConverter

@Database(entities = [CharacterDbo::class, ComicDbo::class, EventDbo::class, SerieDbo::class,
        StoryDbo::class, UrlDbo::class, OffsetDbo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    companion object {

        private const val DATABASE_NAME = "Characters.db"

        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        fun getDatabase(context: Context): CharactersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}