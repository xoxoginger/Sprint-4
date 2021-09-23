package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val resultZones = ZoneId.getAvailableZoneIds().filter { (TimeZone.getTimeZone(it).rawOffset) % 3600000 != 0 }.sorted() //
    return resultZones.toSet() //emptySet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val resultLastDayInMonth = ArrayList<String>(12)
    for(i in 1..12) {
        resultLastDayInMonth.add(LocalDate.of(year, i, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString())
    }
    return resultLastDayInMonth //emptyList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var resultFridays13: Int = 0
    for (i in 1..12) {
        if (LocalDate.of(year, i, 13).dayOfWeek == DayOfWeek.FRIDAY) {
            resultFridays13++
        }
    }
    return resultFridays13
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format (DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US))
}

fun main() {
    println(getZonesWithNonDivisibleByHourOffset())
    println(getLastInMonthDayWeekList(2021))
    println(getNumberOfFridayThirteensInYear(2021))
    println(getFormattedDateTime(LocalDateTime.of(2021, 9, 23, 14, 5)))
}


