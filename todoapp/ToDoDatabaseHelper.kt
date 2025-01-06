package com.example.todoapp
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private const val DATABASE_NAME = "ToDoList.db"
            private const val DATABASE_VERSION = 1
            const val TABLE_NAME = "tasks"
            const val COLUMN_ID = "id"
            const val COLUMN_TASK = "task"
            const val COLUMN_STATUS = "status"
        }

        override fun onCreate(db: SQLiteDatabase?) {
            val createTable = """
                CREATE TABLE $TABLE_NAME (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_TASK TEXT NOT NULL,
                    $COLUMN_STATUS INTEGER DEFAULT 0
                );
            """.trimIndent()
            db?.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

        fun addTask(task: String): Long {
            val db = writableDatabase
            val values = ContentValues()
            values.put(COLUMN_TASK, task)
            return db.insert(TABLE_NAME, null, values)
        }

        fun getAllTasks(): List<Task> {
            val db = readableDatabase
            val cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null
            )
            val tasks = mutableListOf<Task>()
            if (cursor.moveToFirst()) {
                do {
                    tasks.add(
                        Task(
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STATUS)) == 1
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
            return tasks
        }

        fun updateTaskStatus(id: Int, isCompleted: Boolean) {
            val db = writableDatabase
            val values = ContentValues()
            values.put(COLUMN_STATUS, if (isCompleted) 1 else 0)
            db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id.toString()))
        }

        fun deleteTask(id: Int) {
            val db = writableDatabase
            db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
        }
}

data class Task(val id: Int, val task: String, var isCompleted: Boolean)