package com.onurmert.notdefterikotlin.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.notdefterikotlin.Database.IMyDatabase
import com.onurmert.notdefterikotlin.Database.MyDatabaseHelper
import com.onurmert.notdefterikotlin.Model.DbModel
import java.lang.Exception

class CurrentViewModel : ViewModel() {

    val liste = MutableLiveData<ArrayList<DbModel>>()

    fun getAll(context: Context){

        val myDatabaseHelper : IMyDatabase = MyDatabaseHelper(context)

        try {

            liste.value = myDatabaseHelper.getAllData()

        }catch (error: Exception){
            println("akis hepsi okunurken hata oluştu")
            println(error.localizedMessage)
        }
    }

}