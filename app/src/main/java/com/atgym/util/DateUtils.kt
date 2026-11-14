package com.atgym.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

object DateUtils {

    private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val DISPLAY_DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH)

    /**
     * Returns the day index for today: 0=Monday through 6=Sunday (ISO format).
     */
    fun todayDayIndex(): Int {
        return LocalDate.now().dayOfWeek.value - 1
    }

    /**
     * Returns today's date formatted as "yyyy-MM-dd".
     */
    fun todayDateString(): String {
        return LocalDate.now().format(DATE_FORMATTER)
    }

    /**
     * Returns the full day name (e.g., "Monday", "Tuesday", etc.) for the given day index (0=Monday..6=Sunday).
     */
    fun dayName(index: Int): String {
        return DayOfWeek.of((index % 7 + 7) % 7 + 1)
            .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    }

    /**
     * Returns the 3-letter day abbreviation (e.g., "Mon", "Tue", etc.) for the given day index (0=Monday..6=Sunday).
     */
    fun dayShortName(index: Int): String {
        return DayOfWeek.of((index % 7 + 7) % 7 + 1)
            .getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    }

    /**
     * Returns the Monday date of the current week formatted as "yyyy-MM-dd".
     */
    fun currentWeekStart(): String {
        return LocalDate.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .format(DATE_FORMATTER)
    }

    /**
     * Formats a date string from "yyyy-MM-dd" to "MMM d" (e.g., "Nov 14").
     */
    fun formatDate(dateString: String): String {
        val date = LocalDate.parse(dateString.trim(), DATE_FORMATTER)
        return date.format(DISPLAY_DATE_FORMATTER)
    }
}
