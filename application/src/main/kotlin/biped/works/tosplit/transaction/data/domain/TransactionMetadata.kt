package biped.works.tosplit.transaction.data.domain

import biped.works.tosplit.transaction.data.TransactionLocator
import java.time.LocalDate
import java.util.*

data class TransactionMetadata(
    val id: String,
    val owner: String,
    val name: String,
    val description: String,
    val value: Value,
    val recurrence: Recurrence
) {
    val start = recurrence.start
    val conclusion = recurrence.conclusion

    fun createTransactions(timeSpan: TimeSpan = TimeSpan()): List<Transaction> = recurrence.generateDueDates(timeSpan)
        .map { dueDate ->
            Transaction(
                id = TransactionLocator(metaId = id, due = dueDate).key,
                owner = owner,
                name = name,
                description = description,
                due = dueDate,
                value = value,
                recurrence = recurrence
            )
        }

    companion object {
        fun fromTransaction(transaction: Transaction) = TransactionMetadata(
            id = TransactionLocator(transaction.id).metaId,
            owner = transaction.owner,
            name = transaction.name,
            description = transaction.description,
            value = transaction.value,
            recurrence = transaction.recurrence
        )
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