package com.example.todoapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer

class CalendarDayWeekContainer(val itemView:View):ViewContainer(itemView) {
            val dayWeekTextView : TextView = itemView.findViewById(R.id.calendar_dayInWeek)
            val dayMonthTextView : TextView = itemView.findViewById(R.id.calendar_dayInMonth)
}