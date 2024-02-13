package com.mlg.gbadmin.utils

import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle

class CalendarUtils {
    static String getCurrentMonth() {
        Integer currentMonth = LocalDate.now().monthValue
        Month.of(currentMonth).getDisplayName(TextStyle.SHORT, Locale.UK).toUpperCase()
    }

    static String getPreviousMonth() {
        Integer previousMonth = LocalDate.now().minusMonths(1).monthValue
        Month.of(previousMonth).getDisplayName(TextStyle.SHORT, Locale.UK).toUpperCase()
    }
}
