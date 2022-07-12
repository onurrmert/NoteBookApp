package com.onurmert.notdefterikotlin.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onurmert.notdefterikotlin.Model.DbModel
import com.onurmert.notdefterikotlin.MyDate
import com.onurmert.notdefterikotlin.R
import com.onurmert.notdefterikotlin.ViewModel.SaveViewModel

class SavePage : AppCompatActivity() {

    private lateinit var viewModel: SaveViewModel

    private lateinit var edit_note : EditText

    private lateinit var edit_title : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_page)

        viewModel = ViewModelProviders.of(this).get(SaveViewModel::class.java)

        bottomMenu()

        init()

    }
    private fun init(){
        edit_title = findViewById(R.id.edit_title_save)
        edit_note = findViewById(R.id.edit_note_save)
    }

    private fun bottomMenu(){

        val bottom_menu = findViewById<BottomNavigationView>(R.id.bottom_menu_save)

        bottom_menu.setOnNavigationItemReselectedListener {
            item->

            when(item.itemId){
                R.id.notes_menu->{
                    onBackPressed()
                }
            }
        }
    }

    fun saveButton(view : View){

        val title = edit_title.text.toString().trim()
        val note = edit_note.text.toString().trim()
        val history = MyDate.myDate(this)

        if (title != "" && note != ""){
            save(DbModel(1, title, note, history))
            onBackPressed()
        }else{
            Toast.makeText(this, "Boş yerleri doldurun", Toast.LENGTH_SHORT).show()
        }
    }

    private fun save(dbModel: DbModel){
        viewModel.save(dbModel,this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, CurrentPage::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.lefttorigth1, R.anim.lefttorigth2)
        finish()
    }
}