package biped.works.tosplit.core

import java.time.*
import java.time.chrono.ChronoLocalDate
import java.time.format.DateTimeFormatter

private val UTC_ZONE = ZoneId.of("UTC")
const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun String.toLocalDate(pattern: String = ISO_8601): LocalDate {
    val temporalAccessor = DateTimeFormatter.ofPattern(pattern)
        .withZone(UTC_ZONE)
        .parse(this)

    return LocalDate.from(temporalAccessor)
}

fun LocalDate.format(pattern: String = ISO_8601): String {
    return DateTimeFormatter.ofPattern(pattern)
        .withZone(UTC_ZONE)
        .format(this.atStartOfDay())
}

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

fun LocalDate.withAdjustableDayOfMonth(dayOfMonth: Int): LocalDate {
    val lastDayOfMonth = month.length(isLeapYear)
    return if (dayOfMonth < lastDayOfMonth) withDayOfMonth(dayOfMonth) else withDayOfMonth(lastDayOfMonth)
}