package com.onurmert.notdefterikotlin.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.onurmert.notdefterikotlin.Database.MyDatabaseHelper
import com.onurmert.notdefterikotlin.Model.DbModel
import com.onurmert.notdefterikotlin.R
import com.onurmert.notdefterikotlin.View.UPdatePage
import com.onurmert.notdefterikotlin.View.NoteDetailPage

class CurrentRecyclerAdapter (
    val listeDbModel : ArrayList<DbModel>,
    val activity: Activity,
    val view: View) : RecyclerView.Adapter<CurrentRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder (val row: View) : RecyclerView.ViewHolder(row) {

        val text_title = row.findViewById<TextView>(R.id.text_title)
        val text_history = row.findViewById<TextView>(R.id.text_history)
        val popup_button = row.findViewById<ImageView>(R.id.image_popup)

        val recycler_row = row.findViewById<LinearLayout>(R.id.recycler_row)

        fun bindItem(dbModel: DbModel){
            text_title.text = dbModel.title
            text_history.text = dbModel.history

            recycler_row.setOnClickListener {
                goNoteDetail(dbModel.id)
            }
        }
        private fun goNoteDetail(id : Int){
            val intent = Intent(row.context, NoteDetailPage::class.java)
            intent.putExtra("id", id)
            row.context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.current_recycler_row, parent, false)

        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItem(listeDbModel.get(position))

        holder.popup_button.setOnClickListener {
            popup(it, listeDbModel.get(position), holder.itemView.context, position)
        }
    }

    override fun getItemCount(): Int {
        return listeDbModel.size
    }

    private fun popup(view : View, dbModel: DbModel, context: Context, position: Int){

        val popup = PopupMenu(activity, view)

        popup.inflate(R.menu.popup_menu)

        popup.setOnMenuItemClickListener {
                item->
            when(item.itemId){
                R.id.delete_popup ->{
                    deleteQuestion(dbModel, position, context)
                    true
                }
                R.id.update_popup ->{
                    goUpdate(dbModel)
                    true
                }
                else -> true
            }
        }
        popup.show()
    }

    private fun goUpdate(dbModel: DbModel){
        val intent = Intent(activity, UPdatePage::class.java)
        intent.putExtra("id", dbModel.id)
        activity.startActivity(intent)
        activity.finish()
    }

    @SuppressLint("ShowToast")
    private fun deleteQuestion(dbModel: DbModel, position: Int, context: Context){

        Snackbar.make(view, "Silinsin mi?", Snackbar.LENGTH_SHORT)
            .setAction("Evet", View.OnClickListener {
                deleteAdapter(dbModel, position, context)
            })
            .show()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteAdapter(dbModel: DbModel, position: Int, context: Context) {

        listeDbModel.removeAt(position)

        val myDatabaseHelper = MyDatabaseHelper(context)
        myDatabaseHelper.delete(dbModel)

        this.notifyDataSetChanged()
        Toast.makeText(context, "Silindi", Toast.LENGTH_SHORT).show()
        println("silindi")
    }
}