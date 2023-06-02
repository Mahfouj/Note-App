package com.example.myreycycle

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.set
import androidx.room.Room
import com.example.myreycycle.databinding.FragmentDcotorBinding
import java.sql.Time
import java.util.Calendar

class dcotorFragment : Fragment(),AdapterView.OnItemSelectedListener {

    var Protilist = listOf("What,s your praority", "hight", "low", "Mdieum")

    lateinit var binding: FragmentDcotorBinding
    private var SelectedDate: String? = null
    private var time: String? = null
    var priority: String? = null
    private var noteid = 0

   companion object {
        var NOTE_ID = "note_id"
    }

    lateinit var note: Note
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDcotorBinding.inflate(inflater, container, false)

      //  NOTE_ID= requireArguments().getInt(noteid)

        if (noteid != 0) {

            var note = NoteDatabase.getDB(requireContext()).getNoteDao()
                .getByNoteId(listOf<Int>(noteid))[0]

            binding.apply {

                textDtor.setText(note.title)
                btnTime.text = note.time
                btnDate.text = note.date
            }


        }


        var spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Protilist)
        binding.spnDr.adapter = spinnerAdapter

        binding.spnDr.onItemSelectedListener = this@dcotorFragment

        binding.btnDate.setOnClickListener {

            pikeADate()

        }


        binding.btnTime.setOnClickListener {

            pikeATime()
        }

        binding.btnSub.setOnClickListener {

            var titlestr = binding.textDtor.text.toString()
            var timestr = time ?: "00:00"
            var selectedstr = SelectedDate ?: "0/0/00"
            var prioitystr = priority ?: Protilist[3]


            var note = Note(
                title = titlestr,
                time = timestr,
                date = selectedstr,
                priority = prioitystr
            )

            if (noteid == 0) {

                NoteDatabase.getDB(requireContext()).getNoteDao().insetData(note)
            } else {
                note.noteId=noteid
                NoteDatabase.getDB(requireContext()).getNoteDao().updateNoe(note)
            }

        }

        return binding.root


}


    private fun pikeATime() {

         val c =Calendar.getInstance()
         val hour= c.get(Calendar.HOUR_OF_DAY)
         val Minute= c.get(Calendar. MINUTE)

             val timePickerDialog = TimePickerDialog(requireContext(),
                { _, hourOfDay,Minute ->

               time ="$hourOfDay:$Minute"

                    binding.btnTime.text= time

                 },
                hour,Minute,true


                )

              timePickerDialog.show()

    }

    private fun pikeADate() {

        val calendar = Calendar.getInstance()

        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val dialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener{_, month,year,dayOfMonth ->


               SelectedDate="$dayOfMonth/$month/$year"

             binding.btnDate.text=  SelectedDate
            },
        year,month,date)

        dialog.show()

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        priority="${Protilist[position]}"

        Toast.makeText(requireContext(),
            priority,Toast.LENGTH_SHORT).show()


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}
