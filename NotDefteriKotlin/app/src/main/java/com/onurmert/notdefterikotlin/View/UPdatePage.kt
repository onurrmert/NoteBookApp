package com.onurmert.notdefterikotlin.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onurmert.notdefterikotlin.Model.DbModel
import com.onurmert.notdefterikotlin.MyDate
import com.onurmert.notdefterikotlin.R
import com.onurmert.notdefterikotlin.ViewModel.UpdateViewModel

class UPdatePage : AppCompatActivity() {

    private lateinit var viewModel: UpdateViewModel

    private lateinit var edit_title : EditText

    private lateinit var edit_note : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_page)

        init()
    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProviders.of(this).get(UpdateViewModel::class.java)

        viewModel.onlyOneRead(getId(), this)

        readOnlyOne()
    }

    private fun init(){
        edit_title = findViewById(R.id.edit_title_update)
        edit_note = findViewById(R.id.edit_note_update)
    }

    private fun getId(): Int{

        val intent = intent

        return intent.getIntExtra("id", 0)
    }

    fun updateButton(view: View){

        val title = edit_title.text.toString().trim()
        val note = edit_note.text.toString().trim()
        val history = MyDate.myDate(this)

        if (title != "" && note != ""){
            update(DbModel(getId(), title, note, history))
            Toast.makeText(this, "Güncellendi", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }else{
            Toast.makeText(this,"Boş yerleri doldurun", Toast.LENGTH_SHORT).show()
        }
    }

    private fun update(dbModel: DbModel){
        viewModel.update(dbModel, this)
    }

    private fun readOnlyOne(){

        viewModel.currentData.observe(this, Observer {
            item->
            edit_title.setText(item.title)
            edit_note.setText(item.note)
        })
    }

    override fun onBackPressed() {
        val intent = Intent(this, CurrentPage::class.java)
        startActivity(intent)
        finish()
    }
}