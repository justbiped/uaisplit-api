package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.util.UUID

data class TransactionMetadata(
    val id: String,
    val name: String,
    val description: String,
    val entry: LocalDate,
    val conclusion: LocalDate,
    val value: BigDecimal,
    val recurrence: Recurrence
) {
    fun createTransactions(range: Range = Range()): List<Transaction> = when (recurrence.frequency) {
        Frequency.MONTH -> createMonthTransactions(range)
        Frequency.CUSTOM -> createCustomTransactions(range)
        else -> emptyList()
    }

    private fun createCustomTransactions(range: Range): List<Transaction> {
        var due = getDueDate(range)
        val transactions = mutableListOf<Transaction>()

        while (due.isBeforeOrEquals(range.conclusion)) {
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

    private fun createMonthTransactions(range: Range): List<Transaction> {
        var due = getDueDate(range)

        val endDate = DateTools.min(conclusion, range.conclusion)
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

    private fun getDueDate(range: Range): LocalDate =
        if (range.entry.isAfter(entry)) {
            range.entry.withDayOfMonth(entry.dayOfMonth)
        } else entry

}

data class Range(
    val entry: LocalDate = LocalDate.MIN,
    val conclusion: LocalDate = LocalDate.MAX
)

object DateTools {
    fun min(first: LocalDate, second: LocalDate) = if (first.isBefore(second)) first else second
}

fun LocalDate.isBeforeOrEquals(other: ChronoLocalDate): Boolean {
    return isBefore(other) || this == other
}