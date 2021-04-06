package com.guide.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDateSelecting.setOnClickListener{view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    Toast.makeText(this, "Datinggg $selectedYear", Toast.LENGTH_LONG).show()

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                    tvSelectedDate.setText(selectedDate)

                    val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes = theDate!!.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    print("Yoooooooooo: $currentDate")

                    Log.i("Hello", "$currentDate")

                    val currentDateInMinutes = currentDate!!.time / 60000

                    val ageInMinutes = currentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinutes.setText(ageInMinutes.toString())
                },
                year,
                month,
                dayOfMonth)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}