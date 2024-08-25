package com.example.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.database.dao.TaskDao
import com.example.todoapp.database.model.Task
import com.example.todoapp.database.typeConverter.DateConverter

@Database(entities = [Task::class], version = 4)
@TypeConverters(DateConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null
        private const val DATA_BASE_NAME = "Tasks Database"
        fun getInstance(context: Context): TaskDatabase {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(context, TaskDatabase::class.java, DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return INSTANCE!!
        }
    }
}