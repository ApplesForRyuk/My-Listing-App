package com.applesforryuk.mylistmaking

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ToDoListAdapter.TodoListClickListener {

    private lateinit var binding: ActivityMainBinding

    private val listDataManager: ListDataManager = ListDataManager(this)

    companion object {
        const val INTENT_LIST_KEY = "list"
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        val lists = listDataManager.readLists()

        binding.contentMain.listsRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.contentMain.listsRecyclerview.adapter = ToDoListAdapter(lists,this)

        binding.fab.setOnClickListener { _ ->
            showCreateToDoListDialog()

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showCreateToDoListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)
        val myDialog = AlertDialog.Builder(this)
        val toDoTitleEditText = EditText(this)
        toDoTitleEditText.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        myDialog.setTitle(dialogTitle)
        myDialog.setView(toDoTitleEditText)

        myDialog.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            val adapter = binding.contentMain.listsRecyclerview.adapter as ToDoListAdapter
            val list = TaskList(toDoTitleEditText.text.toString())
            listDataManager.saveList(list)

            adapter.addList(list)
            //adapter.addNewItem(toDoTitleEditText.text.toString())
            dialog.dismiss()
            //after dismissing dialog user is brought to the new activity
            showTaskListItems(list)
        }
        myDialog.create().show()
    }

        //creating an Intent and an extra

    private fun showTaskListItems(list: TaskList) {
        val taskListItem = Intent(this, DetailActivity::class.java)
        taskListItem.putExtra(INTENT_LIST_KEY, list)
        startActivity(taskListItem)

    }

    override fun listItemClicked(list: TaskList) {
        showTaskListItems(list)
    }


}


