package com.manohar.colaninfotechtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.manohar.colaninfotechtask.db.Todo
import com.manohar.colaninfotechtask.db.TodoDatabase
import com.manohar.colaninfotechtask.repository.TodoRepository
import com.manohar.colaninfotechtask.util.Validator
import com.manohar.colaninfotechtask.viewmodel.HomeViewModel
import com.manohar.colaninfotechtask.viewmodel.HomeViewModelFactory

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import com.manohar.colaninfotechtask.R
import java.util.*

import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.manohar.colaninfotechtask.databinding.FragmentAddTodoBinding


class AddTodoFragment : Fragment() {

    private lateinit var fragmentTodoFragment: FragmentAddTodoBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_update_todo, container, false)
        fragmentTodoFragment = FragmentAddTodoBinding.inflate(inflater,container,false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val dao = TodoDatabase.getDatabase(requireActivity()).todoDao()
        val repository = TodoRepository(dao)
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        fragmentTodoFragment.addTodoBtn.setOnClickListener {
            validateDetails()
        }

        fragmentTodoFragment.timeEt.setOnClickListener {
            showTimeDialog()
        }

        fragmentTodoFragment.dateEt.setOnClickListener {
            showDateDialog()
        }

        return fragmentTodoFragment.root
    }

    private fun showTimeDialog() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(childFragmentManager,"Task")

        picker.addOnPositiveButtonClickListener{
            if (picker.hour > 12){
                fragmentTodoFragment.timeEt.setText(String.format("%02d", picker.hour - 12) + " : " + String.format("%02d", picker.minute) + " PM")
            }else{
                fragmentTodoFragment.timeEt.setText(String.format("%02d", picker.hour) + " : " + String.format("%02d", picker.minute) + " AM")
            }

            val calender = Calendar.getInstance()
            calender[Calendar.HOUR_OF_DAY] = picker.hour
            calender[Calendar.MINUTE] = picker.minute
            calender[Calendar.SECOND] = 0
            calender[Calendar.MILLISECOND] = 0
        }
    }

    private fun showDateDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val mYear: Int = calendar.get(Calendar.YEAR)
        val mMonth: Int = calendar.get(Calendar.MONTH)
        val mDay: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(),
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                fragmentTodoFragment.dateEt.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
            }, mYear, mMonth, mDay
        )
        val datePicker = datePickerDialog.datePicker
        datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun validateDetails(){
        if (Validator.validateEmptyCheckOfEditText(fragmentTodoFragment.titleEt.text.toString())){
            if (Validator.validateEmptyCheckOfEditText(fragmentTodoFragment.descriptionEt.text.toString())){
                if (Validator.validateEmptyCheckOfEditText(fragmentTodoFragment.timeEt.text.toString())){
                    if (Validator.validateEmptyCheckOfEditText(fragmentTodoFragment.dateEt.text.toString())){
                        homeViewModel.insertTodo(Todo(
                            0,
                            fragmentTodoFragment.titleEt.text.toString(),
                            fragmentTodoFragment.descriptionEt.text.toString(),
                            fragmentTodoFragment.timeEt.text.toString(),
                            fragmentTodoFragment.dateEt.text.toString(),
                            getAlarmType(fragmentTodoFragment.radioGroup)
                        ))
                        navController.navigate(R.id.action_addUpdateTodoFragment_to_homeTodoListFragment)
                    }else{ showEditTextError(fragmentTodoFragment.dateEt) }
                }else{ showEditTextError(fragmentTodoFragment.timeEt) }
            }else{ showEditTextError(fragmentTodoFragment.descriptionEt) }
        }else{ showEditTextError(fragmentTodoFragment.titleEt) }
    }

    private fun showEditTextError(editText: EditText){
        editText.requestFocus()
        editText.setError(resources.getString(R.string.enter_valid_details))
    }

    private fun getAlarmType(radioGroup : RadioGroup) : String{
        var selectedValue = ""
        when(radioGroup.checkedRadioButtonId){
            R.id.radioBtnDaily -> {selectedValue = "Daily"}
            R.id.radioBtnWeekly -> {selectedValue = "Weekly"}
        }

        return selectedValue
    }
}