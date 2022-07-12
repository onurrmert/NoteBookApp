package com.onurmert.notdefterikotlin.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.onurmert.notdefterikotlin.R
import com.onurmert.notdefterikotlin.ViewModel.NoteDetailViewModel

class NoteDetailPage : AppCompatActivity() {

    private lateinit var text_title : TextView

    private lateinit var text_note : TextView

    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail_page)

        init()
    }

    private fun init(){
        text_title = findViewById(R.id.text_title_note_detail)
        text_note = findViewById(R.id.text_note_note_detail)
    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)

        viewModel.getOneNote(getId(), this)

        getNot()
    }

    private fun getId(): Int{

        val intent = intent

        return intent.getIntExtra("id", 0)
    }

    private fun getNot(){

        viewModel.dbModel.observe(this, Observer {
            item ->
                text_title.setText(item.title)
                text_note.setText(item.note)
        })
    }
}