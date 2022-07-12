package com.onurmert.notdefterikotlin.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onurmert.notdefterikotlin.Adapter.CurrentRecyclerAdapter
import com.onurmert.notdefterikotlin.Model.DbModel
import com.onurmert.notdefterikotlin.R
import com.onurmert.notdefterikotlin.ViewModel.CurrentViewModel

class CurrentPage : AppCompatActivity() {

    private lateinit var viewModel: CurrentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_page)

        viewModel = ViewModelProviders.of(this).get(CurrentViewModel::class.java)

        viewModel.getAll(this)

        bottomMenu()
    }

    override fun onResume() {
        super.onResume()
        getAllCurrent()
    }

    private fun bottomMenu(){
        val bottom_menu = findViewById<BottomNavigationView>(R.id.bottom_menu_current)

        bottom_menu.setOnNavigationItemReselectedListener {
            item ->

            when(item.itemId){
                R.id.save_menu ->{
                    val intent = Intent(this, SavePage::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.righttoleft1, R.anim.rigthtoleft2)
                    finish()
                }
            }
        }
    }

    private fun getAllCurrent(){
        viewModel.liste.observe(this, Observer {
            item ->
            createRecycler(item)
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun createRecycler(liste: ArrayList<DbModel>){

        val current_view = findViewById<ConstraintLayout>(R.id.current_pagei_view)

        val adapter = CurrentRecyclerAdapter(liste, this, current_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}