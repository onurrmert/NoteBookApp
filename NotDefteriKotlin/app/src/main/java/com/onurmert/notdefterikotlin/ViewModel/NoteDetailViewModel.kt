package com.onurmert.notdefterikotlin.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.notdefterikotlin.Database.IMyDatabase
import com.onurmert.notdefterikotlin.Database.MyDatabaseHelper
import com.onurmert.notdefterikotlin.Model.DbModel

class NoteDetailViewModel: ViewModel() {

    val dbModel = MutableLiveData<DbModel>()

    fun getOneNote(id: Int, context: Context){

        val myDatabaseHelper : IMyDatabase = MyDatabaseHelper(context)

        dbModel.value = myDatabaseHelper.getOnlyOne(id)
    }
}