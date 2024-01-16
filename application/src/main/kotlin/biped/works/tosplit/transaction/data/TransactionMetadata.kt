package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.time.chrono.ChronoLocalDate
import java.util.*

data class TransactionMetadata(
    val id: String,
    val name: String,
    val description: String,
    val start: LocalDate,
    val conclusion: LocalDate,
    val value: BigDecimal,
    val recurrence: Recurrence
) {
    val startDay = start.dayOfMonth

    fun createTransactions(timeSpan: TimeSpan = TimeSpan()): List<Transaction> = when (recurrence.frequency) {
        Frequency.MONTH -> createMonthTransactions(timeSpan)
        Frequency.CUSTOM -> createCustomTransactions(timeSpan)
        else -> emptyList()
    }

    private fun createCustomTransactions(timeSpan: TimeSpan): List<Transaction> {
        var due = getDueDate(timeSpan)
        val transactions = mutableListOf<Transaction>()

        while (due.isBeforeOrEquals(timeSpan.end)) {
            val transaction = transaction(
                id = UUID.randomUUID().toString(),
                metaId = id,
                name = name,
                description = description,
                due = due,
                value = value
            )

            transactions.add(transaction)
            due = due.plusDays(recurrence.interval.toLong())
        }

        return transactions
    }

    private fun createMonthTransactions(timeSpan: TimeSpan): List<Transaction> {
        var due = recurrence.nextDueDate(timeSpan)

        val endDate = DateTools.min(conclusion, timeSpan.end)
        val transactions = mutableListOf<Transaction>()

        while (due.isBeforeOrEquals(endDate)) {
            val transaction = transaction(
                id = UUID.randomUUID().toString(),
                metaId = id,
                name = name,
                description = description,
                due = due,
                value = value
            )

            transactions.add(transaction)
            due = due.plusMonths(1)
        }

        return transactions
    }

    private fun getDueDate(timeSpan: TimeSpan): LocalDate {
        return if (timeSpan.start.isBeforeOrEquals(start)) start else createDueDate(timeSpan)
    }

    private fun createDueDate(timeSpan: TimeSpan): LocalDate {
        val dueDate = if (timeSpan.startDay > startDay) timeSpan.start.plusMonths(1) else timeSpan.start
        return dueDate.withAdjustableDayOfMonth(startDay)
    }

}

fun LocalDate.withAdjustableDayOfMonth(dayOfMonth: Int): LocalDate {
    val lastDayOfMonth = month.length(isLeapYear)
    return if (dayOfMonth < lastDayOfMonth) withDayOfMonth(dayOfMonth) else withDayOfMonth(lastDayOfMonth)
}

data class TimeSpan(
    val start: LocalDate = LocalDate.MIN,
    val end: LocalDate = LocalDate.MAX
) {
    val startDay = start.dayOfMonth
}

data class Period(val month: Month, val year: Int)

object DateTools {
    fun min(first: LocalDate, second: LocalDate) = if (first.isBefore(second)) first else second
    fun max(first: LocalDate, second: LocalDate) = if (first.isAfter(second)) first else second
}

fun LocalDate.isBeforeOrEquals(other: ChronoLocalDate): Boolean {
    return isBefore(other) || this == other
}