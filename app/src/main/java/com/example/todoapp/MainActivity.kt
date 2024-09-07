package com.example.todoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
//import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.todoapp.model.UserData
import com.example.todoapp.view.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //set list
        userList = ArrayList()
        //set id
        addsBtn = findViewById(R.id.addingBtn)

        recv = findViewById(R.id.mRecycler)
        //set adapter
        userAdapter = UserAdapter(this, userList)
        //set recycler view adapter
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter
        //set dialog
        addsBtn.setOnClickListener { addInfo() }

        // Observe LiveData
        userViewModel.allUser.observe(this, Observer { users ->
            users?.let {
                userList.clear()
                userList.addAll(it)
                userAdapter.notifyDataSetChanged()
            }
        })
    }
    fun updateInfo(userData: UserData) {
        userViewModel.update(userData)
    }
    fun deleteInfo(userData: UserData) {
        userViewModel.delete(userData)
    }
    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        //set view
        val AddSubject = v.findViewById<EditText>(R.id.addSubject)
        val AddTask = v.findViewById<EditText>(R.id.addTask)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val subjects = AddSubject.text.toString()
            val tasks = AddTask.text.toString()
            if (subjects.isNotBlank() && tasks.isNotBlank()) {
                val userData = UserData(Subject = subjects, Task = tasks)
                userViewModel.insert(userData) // Use ViewModel to insert data
                Toast.makeText(this, "$subjects was added Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter both subject and task", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_icon -> {
                // Handle search action
                true
            }
            R.id.favorite_icon -> {
                // Handle favourite action
                true
            }
            R.id.sort_icon -> {
                // Handle sorting action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}