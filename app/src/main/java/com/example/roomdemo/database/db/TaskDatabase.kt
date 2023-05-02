package com.example.roomdemo.database.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdemo.database.dao.TaskDao
import com.example.roomdemo.database.dao.TaskDescriptionDao
import com.example.roomdemo.database.models.TaskDescription
import com.example.roomdemo.database.models.TaskItem

@Database(
    entities = [TaskItem::class, TaskDescription::class],
    version = 5,
    exportSchema = true,
    autoMigrations = [AutoMigration(
        from = 1, to = 2, TaskDatabase.MyAutoMigration::class
    ), AutoMigration(
        from = 2, to = 3, TaskDatabase.MyAuto2To3Migration::class
    ), AutoMigration(from = 3, to = 4, TaskDatabase.MyAuto3To4Migration::class)]
)
public abstract class TaskDatabase : RoomDatabase() {

    // for first migration 1 to 2 with adding just one column time
    class MyAutoMigration : AutoMigrationSpec

    // for second migration 2 to 3 with rename just one column name time to createdAt.
    @RenameColumn("Task", "time", "createdAt")
    class MyAuto2To3Migration : AutoMigrationSpec

    // for third migration 3 to 4 with adding just one column updatedAt
    class MyAuto3To4Migration : AutoMigrationSpec

    abstract fun taskDao(): TaskDao

    abstract fun taskDescriptionDao(): TaskDescriptionDao

    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TaskDatabase? = null


        // manual migration with adding one extra table in db.
        private val MyAuto4To5Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS TaskDescription (id INTEGER PRIMARY KEY NOT NULL,taskId INTEGER,description TEXT, createdAt TEXT,updatedAt TEXT)")
            }
        }

        fun getDatabase(context: Context): TaskDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, TaskDatabase::class.java, "TaskDatabase"
                ).addMigrations(TaskDatabase.MyAuto4To5Migration).build()
                INSTANCE = instance
                instance
            }
        }
    }
}