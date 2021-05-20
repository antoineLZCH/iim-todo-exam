package com.antoinelzch.chosesafaire.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antoinelzch.chosesafaire.data.models.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}