package com.applesforryuk.mylistmaking

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager

class ListDataManager(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    fun saveList(list: TaskList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
        sharedPrefs.apply()
    }

    fun readLists(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in contents) {
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, taskItems)
            taskLists.add(list)
        }

        return taskLists
    }

    fun deleteList(list: TaskList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.remove(list.name)
        sharedPrefs.apply()
    }

}
