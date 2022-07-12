package com.onurmert.notdefterikotlin.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.onurmert.notdefterikotlin.Model.DbModel

class MyDatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, databaseName, null, databaseVersion), IMyDatabase{

    companion object{
        val databaseName = "noteSave"
        val databaseVersion = 2
        val tableName = "notTable"
        val idColumn = "id_"
        val titleColumn = "title_"
        val noteColumn = "notee_"
        val historyColumn = "history_"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {

        val sql_create = "CREATE TABLE ${tableName} (${idColumn} INTEGER PRIMARY KEY AUTOINCREMENT, ${titleColumn} TEXT, ${noteColumn} TEXT, ${historyColumn} TEXT)"

        if (sqLiteDatabase != null){
            sqLiteDatabase.execSQL(sql_create)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        onCreate(p0)
    }

    override fun insert(dbModel: DbModel) {

        val sqLiteDatabase : SQLiteDatabase = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(historyColumn, dbModel.history)
        contentValues.put(titleColumn, dbModel.title)
        contentValues.put(noteColumn, dbModel.note)

        val sonuc = sqLiteDatabase.insertOrThrow(tableName, null, contentValues)

        sqLiteDatabase.close()
    }

    override fun getAllData(): ArrayList<DbModel> {
        val sqLiteDatabase = this.readableDatabase
        val list1 = ArrayList<DbModel>()

        val sql_read = "SELECT * FROM ${tableName} ORDER BY ${idColumn} DESC"

        val cursor = sqLiteDatabase.rawQuery(sql_read, null)

        if (cursor.count > 0){

            while (cursor.moveToNext()){

                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val note = cursor.getString(2)
                val history = cursor.getString(3)

                val dbModel = DbModel(id, title, note, history)

                list1.add(dbModel)

            }
            cursor.close()
        }else{
            println("kayıtlı veri yok")
        }
        sqLiteDatabase.close()
        return list1
    }

    override fun getOnlyOne(id: Int): DbModel {
        val sqLiteDatabase = this.readableDatabase

        val sql_read = "SELECT * FROM ${tableName} WHERE ${idColumn} = ${id}"

        val cursor = sqLiteDatabase.rawQuery(sql_read, null)

        var id : Int ? = null
        var title : String ? = null
        var note : String ? = null
        var history : String ? = null

        while (cursor.moveToNext()) {

            id = cursor.getInt(0)
            title = cursor.getString(1)
            note = cursor.getString(2)
            history = cursor.getString(3)
        }
        cursor.close()
        sqLiteDatabase.close()
        return DbModel(id!!, title!!, note!!, history!!)
    }

    override fun update(dbModel: DbModel) {
        val sqLiteDatabase = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(titleColumn, dbModel.title)
        contentValues.put(noteColumn, dbModel.note)
        contentValues.put(historyColumn, dbModel.history)

        sqLiteDatabase.update(
            tableName,
            contentValues,
            idColumn + " = ? ",
            arrayOf(dbModel.id.toString())
            )
        sqLiteDatabase.close()
    }

    override fun delete(dbModel: DbModel) {

        val sqliDatabase = this.writableDatabase

        sqliDatabase.delete(tableName, "${idColumn} = ? ", arrayOf<String>(dbModel.id.toString()))

        sqliDatabase.close()
    }
}