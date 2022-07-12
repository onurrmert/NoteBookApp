package com.onurmert.notdefterikotlin.Database

import com.onurmert.notdefterikotlin.Model.DbModel

interface IMyDatabase {

    fun insert(dbModel: DbModel)

    fun getAllData() : ArrayList<DbModel>

    fun getOnlyOne(id: Int) : DbModel

    fun update(dbModel: DbModel)

    fun delete(dbModel: DbModel)
}