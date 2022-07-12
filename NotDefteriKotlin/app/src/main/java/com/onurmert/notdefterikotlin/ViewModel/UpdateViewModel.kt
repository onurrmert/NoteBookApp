package com.onurmert.notdefterikotlin.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.notdefterikotlin.Database.IMyDatabase
import com.onurmert.notdefterikotlin.Database.MyDatabaseHelper
import com.onurmert.notdefterikotlin.Model.DbModel
import java.lang.Exception

class UpdateViewModel : ViewModel()  {

    val currentData = MutableLiveData<DbModel>()

    fun update(dbModel: DbModel, context: Context){

        val myDatabaseHelper : IMyDatabase = MyDatabaseHelper(context)

        try {

            myDatabaseHelper.update(dbModel)

        }catch (exception: Exception){
            println("güncellenirken hata oluştu")
            println(exception.localizedMessage)
        }
    }

    fun onlyOneRead(id: Int, context: Context){

        val myDatabaseHelper : IMyDatabase = MyDatabaseHelper(context)

        try {
            currentData.value = myDatabaseHelper.getOnlyOne(id)

        }catch (error : Exception){
            println("guncelle tek veri okunurken hata oluştu")
            println(error.localizedMessage)
        }
    }
}