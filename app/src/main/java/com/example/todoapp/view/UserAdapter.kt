package com.example.todoapp.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import com.example.todoapp.model.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.MainActivity
import com.example.todoapp.R

class UserAdapter(val c: Context, val userList:ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{
    inner class UserViewHolder(val v: View): RecyclerView.ViewHolder(v){
        var subject: TextView
        var task: TextView
        var mMenus: ImageView
        init {
            subject=v.findViewById<TextView>(R.id.show_subject)
            task=v.findViewById<TextView>(R.id.show_task)
            mMenus=v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{popupMenus(it)}
        }
        private fun popupMenus(v: View) {
            val position=userList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editeText ->{
                        val v= LayoutInflater.from(c).inflate(R.layout.add_item,null)
                        val subjectTask=v.findViewById<EditText>(R.id.addSubject)
                        val contentTask=v.findViewById<EditText>(R.id.addTask)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){ dialog,_->
                                val updatedSubject = subjectTask.text.toString()
                                val updatedTask = contentTask.text.toString()
                                val updatedUserData = position.copy(Subject = updatedSubject, Task = updatedTask)
                                (c as MainActivity).updateInfo(updatedUserData) // Update through ViewModel
                                Toast.makeText(c,"Update is Done", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancle"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete ->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setMessage("Are you sure")
                            .setPositiveButton("Yes"){ dialog,_->
                                (c as MainActivity).deleteInfo(position) // Delete through ViewModel
                                Toast.makeText(c, "The data was deleted successfully", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){ dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    else->true
                }
            }
            popupMenus.show()
            val popup= PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible=true
            val menu=popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.list_item,parent,false)
        return UserViewHolder(v)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList=userList[position]
        holder.subject.text=newList.Subject
        holder.task.text=newList.Task
    }
    override fun getItemCount():Int{
        return userList.size
    }
}