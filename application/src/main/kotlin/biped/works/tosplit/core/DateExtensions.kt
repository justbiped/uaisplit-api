package biped.works.tosplit.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.chrono.ChronoLocalDate

fun String.toLocalDate(): LocalDate = LocalDate.parse(this)

fun LocalDate.toEpochSecond(offset: ZoneOffset = ZoneOffset.UTC): Long {
    return LocalDateTime
        .of(this, LocalTime.of(0, 0))
        .toEpochSecond(offset)
}

object DateTools {
    fun min(first: LocalDate, second: LocalDate): LocalDate = if (first.isBefore(second)) first else second
    fun max(first: LocalDate, second: LocalDate): LocalDate = if (first.isAfter(second)) first else second
}

fun LocalDate.isBeforeOrEquals(other: ChronoLocalDate): Boolean {
    return isBefore(other) || this == other
}