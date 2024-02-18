package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RemoteRecurrence
import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import kotlin.math.absoluteValue

fun RemoteTransactionMetadata.toDomain(id: String) = TransactionMetadata(
    id = id,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence.toDomain(start, conclusion)
)

private fun RemoteRecurrence.toDomain(
    start: BigDecimal,
    conclusion: BigDecimal
): Recurrence {
    val recurrenceType = RemoteRecurrence.Type.valueOf(type)
    val recurrence = recurrence(
        start = start.toLocalDate(),
        conclusion = conclusion.toLocalDate(),
        frequency = frequency,
    )

    return when (recurrenceType) {
        RemoteRecurrence.Type.MONTHLY -> MonthlyRecurrence(recurrence)
        else -> throw Exception("Recurrence not supported")
    }
}

private fun BigDecimal.toLocalDate() = Instant
    .ofEpochMilli(this.toLong().absoluteValue)
    .atOffset(ZoneOffset.UTC)
    .toLocalDate()
