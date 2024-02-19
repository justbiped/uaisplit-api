package biped.works.tosplit.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

fun LocalDate.toEpochSecond(offset: ZoneOffset = ZoneOffset.UTC): Long {
    return LocalDateTime
        .of(this, LocalTime.of(0, 0))
        .toEpochSecond(offset)
}