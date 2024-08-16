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
        fun fromTransaction(transaction: Transaction): TransactionMetadata = TransactionMetadata(
            id = getMetaId(transaction),
            owner = transaction.owner,
            name = transaction.name,
            description = transaction.description,
            value = transaction.value,
            recurrence = transaction.recurrence
        )

        private fun getMetaId(transaction: Transaction) =
            if (transaction.id.isNotBlank()) TransactionLocator(transaction.id).metaId else ""
    }
}

data class TimeSpan(
    val start: LocalDate = LocalDate.MIN,
    val end: LocalDate = LocalDate.MAX
) {
    val startDay = start.dayOfMonth
}