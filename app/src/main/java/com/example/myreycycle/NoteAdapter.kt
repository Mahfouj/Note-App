package com.example.myreycycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myreycycle.databinding.ItimViewBinding

class NoteAdapter(var noteEditListener: NoteEditListener,var noteDeleteListener:NoteDeleteListener):
    ListAdapter<Note,NoteAdapter. NoteViewHolder>(comparator) {

         interface NoteEditListener{

            fun  onNoteEdit(note: Note)
         }

   interface NoteDeleteListener{

    fun   onDeleteListener(note: Note)
   }




      class NoteViewHolder(var binding: ItimViewBinding):RecyclerView.ViewHolder(binding.root)

       companion object{

          var comparator= object :DiffUtil.ItemCallback<Note> (){

              override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                 return oldItem==newItem
             }

             override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {

                return  oldItem.noteId==newItem.noteId
             }


          }


       }


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      return NoteViewHolder(ItimViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
   }

   override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

      getItem(position).let {
         holder.binding.apply {

            itimdate.text=it.date
            itimtime.text=it.time
            itimname.text=it.title

         }

          holder.itemView.setOnClickListener {_->
              noteEditListener.onNoteEdit(it)
          }

            holder.binding.noteDeleteBtn.setOnClickListener {_->
                noteDeleteListener.onDeleteListener(it)
            }
            }
      }

   }


