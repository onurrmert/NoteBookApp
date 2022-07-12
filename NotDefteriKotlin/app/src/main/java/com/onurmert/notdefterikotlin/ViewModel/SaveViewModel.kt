package com.onurmert.notdefterikotlin.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.onurmert.notdefterikotlin.Database.IMyDatabase
import com.onurmert.notdefterikotlin.Database.MyDatabaseHelper
import com.onurmert.notdefterikotlin.Model.DbModel
import java.lang.Exception

class SaveViewModel : ViewModel() {

    fun save(dbModel: DbModel, context: Context){

        val myDatabaseHelper : IMyDatabase = MyDatabaseHelper(context)

        try {
            myDatabaseHelper.insert(dbModel)

        }catch (exception : Exception){
            println(exception.localizedMessage)
            println("kaydedilirken hata oluştu")
        }
    }
}