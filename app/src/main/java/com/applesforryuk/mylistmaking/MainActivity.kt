package com.applesforryuk.mylistmaking

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ToDoListFragment.OnFragmentInteractionListener {
    private lateinit var binding: ActivityMainBinding

    private var toDoListFragment = ToDoListFragment.newInstance()




    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)





        binding.fab.setOnClickListener {
            showCreateToDoListDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== LIST_DETAIL_REQUEST_CODE) {
            data?.let{
                val list = data.getParcelableExtra<TaskList>(INTENT_LIST_KEY)
                toDoListFragment.saveList(list)
            }
        }
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

            val list = TaskList(toDoTitleEditText.text.toString())

            toDoListFragment.addList(list)


            dialog.dismiss()
            //after dismissing dialog user is brought to the new activity
            showTaskListItems(list)
        }
        myDialog.create().show()
    }

    // creating an Intent and an extra
    private fun showTaskListItems(list: TaskList) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(INTENT_LIST_KEY, list)
        //here is the activity launched
        startActivityForResult(intent, LIST_DETAIL_REQUEST_CODE)
    }

    override fun onTodoListClicked(list: TaskList) {
        showTaskListItems(list)
    }
}
