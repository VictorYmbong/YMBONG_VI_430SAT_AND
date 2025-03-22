package com.example.multilayoutlogger

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.graphics.Color
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import com.google.android.material.snackbar.Snackbar

class TaskManagerActivity : AppCompatActivity() {
    private lateinit var editTextTask: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var buttonAdd: Button
    private lateinit var buttonClear: Button
    private lateinit var listViewTasks: ListView
    private lateinit var taskList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREF_NAME = "TaskManagerPrefs"
        private const val TASKS_KEY = "tasks"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_manager)

        // Initialize views
        initializeViews()
        // Setup spinner
        setupSpinner()
        // Setup ListView and adapter
        setupListView()
        // Load saved tasks
        loadTasks()
        // Setup click listeners
        setupClickListeners()
    }

    private fun initializeViews() {
        editTextTask = findViewById(R.id.editTextTask)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonClear = findViewById(R.id.buttonClear)
        listViewTasks = findViewById(R.id.listViewTasks)
        taskList = ArrayList()
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.task_categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategory.adapter = adapter
        }
    }

    private fun setupListView() {
        adapter = ArrayAdapter(this, R.layout.task_list_item, taskList)
        listViewTasks.adapter = adapter
        
        listViewTasks.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, taskList[position], Toast.LENGTH_SHORT).show()
        }

        listViewTasks.onItemLongClickListener = OnItemLongClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            saveTasks()
            true
        }
    }

    private fun setupClickListeners() {
        buttonAdd.setOnClickListener {
            addTask()
        }

        buttonClear.setOnClickListener {
            taskList.clear()
            adapter.notifyDataSetChanged()
            saveTasks()
            Snackbar.make(it, "All tasks cleared", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun addTask() {
        val taskText = editTextTask.text.toString().trim()
        if (taskText.isNotEmpty()) {
            val category = spinnerCategory.selectedItem.toString()
            val task = "$taskText - $category"
            taskList.add(task)
            adapter.notifyDataSetChanged()
            editTextTask.text.clear()
            saveTasks()
        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTasks() {
        val editor = sharedPreferences.edit()
        val tasksSet = HashSet(taskList)
        editor.putStringSet(TASKS_KEY, tasksSet)
        editor.apply()
    }

    private fun loadTasks() {
        val tasksSet = sharedPreferences.getStringSet(TASKS_KEY, HashSet())
        taskList.clear()
        taskList.addAll(tasksSet ?: emptySet())
        adapter.notifyDataSetChanged()
    }
} 