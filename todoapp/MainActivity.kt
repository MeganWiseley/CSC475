package com.example.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: ToDoDatabaseHelper
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize database helper
        dbHelper = ToDoDatabaseHelper(this)

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(
            tasks,
            onStatusChange = { task -> dbHelper.updateTaskStatus(task.id, task.isCompleted) },
            onDelete = { task ->
                dbHelper.deleteTask(task.id)
                tasks.remove(task)
                adapter.notifyDataSetChanged()
            }
        )
        recyclerView.adapter = adapter

        // Load tasks from the database
        loadTasks()

        // Add button logic
        val addButton = findViewById<Button>(R.id.addButton)
        val inputTask = findViewById<EditText>(R.id.inputTask)
        addButton.setOnClickListener {
            val taskText = inputTask.text.toString()
            if (taskText.isNotBlank()) {
                val id = dbHelper.addTask(taskText).toInt()
                tasks.add(Task(id, taskText, false))
                adapter.notifyItemInserted(tasks.size - 1)
                inputTask.text.clear()
            }
        }
    }

    private fun loadTasks() {
        tasks.clear()
        tasks.addAll(dbHelper.getAllTasks())
        adapter.notifyDataSetChanged()
    }
}
