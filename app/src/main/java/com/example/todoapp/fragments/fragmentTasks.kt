package com.example.todoapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.todoapp.CalendarDayWeekContainer
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTasksBinding
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class fragmentTasks : Fragment() {
    lateinit var binding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.calendarView.dayBinder = object : WeekDayBinder<CalendarDayWeekContainer> {

                override fun bind(container: CalendarDayWeekContainer, data: WeekDay) {
                    container.dayWeekTextView.text = data.date.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    )
                    container.dayMonthTextView.text = "${data.date.dayOfMonth}"

                }

                override fun create(view: View): CalendarDayWeekContainer {
                    return CalendarDayWeekContainer(view)
                }
            }

            val currentDate = LocalDate.now()
            val currentMonth = YearMonth.now()
            val startDate = currentMonth.minusMonths(100).atStartOfMonth() // Adjust as needed
            val endDate = currentMonth.plusMonths(100).atEndOfMonth() // Adjust as needed
            val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
            binding.calendarView.setup(startDate, endDate, firstDayOfWeek)
            binding.calendarView.scrollToWeek(currentDate)

        }
    }


}
