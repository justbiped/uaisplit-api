package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RemoteRecurrence
import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneOffset

fun RemoteTransactionMetadata.toDomain(id: String) = TransactionMetadata(
    id = id,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence.toDomain()
)

private fun RemoteRecurrence.toDomain(): Recurrence {
    val recurrenceType = Recurrence.Type.valueOf(type)
    val recurrence = recurrence(
        start = start.toLocalDate(),
        conclusion = conclusion.toLocalDate(),
        frequency = frequency,
        type = recurrenceType
    )

    return when (recurrenceType) {
        Recurrence.Type.MONTHLY -> MonthlyRecurrence(recurrence)
        else -> throw Exception("Recurrence not supported")
    }
}

private fun BigDecimal.toLocalDate() = Instant
    .ofEpochMilli(this.toLong())
    .atOffset(ZoneOffset.UTC)
    .toLocalDate()
