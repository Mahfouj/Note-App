package com.example.myreycycle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myreycycle.databinding.FragmentHomeBinding


class homeFragment : Fragment(),NoteAdapter.NoteEditListener,NoteAdapter.NoteDeleteListener{

lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

         binding= FragmentHomeBinding.inflate(inflater,container,false)

       NoteDatabase.getDB(requireContext()).getNoteDao().getAllNote()?.let {

          var adapter:NoteAdapter = NoteAdapter(this,this)
       adapter.submitList(it)
          binding.noteRey.adapter=adapter
     }





       binding.addnote.setOnClickListener {


         findNavController().navigate(R.id.action_homeFragment_to_dcotorFragment)
      }




        return binding.root
    }

    override fun onNoteEdit(note: Note) {

        val bundle=Bundle()
        bundle.putInt(dcotorFragment.NOTE_ID,note.noteId)

        findNavController().navigate(R.id.action_homeFragment_to_dcotorFragment,bundle)
    }

    override fun onDeleteListener(note: Note) {

    }


}