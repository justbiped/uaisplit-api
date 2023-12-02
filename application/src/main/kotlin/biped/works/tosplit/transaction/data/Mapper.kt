package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RecurrenceRequest
import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import com.google.cloud.Timestamp
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date

fun TransactionRequest.toDomain(): Transaction {
    val transaction = transaction(
        id = id,
        name = name,
        description = description,
        due = entry,
        value = value,
        recurrence = recurrence.toDomain()
    )

    return when (recurrence.frequency) {
        Frequency.YEAR -> YearTransaction(transaction)
        Frequency.MONTH -> MonthTransaction(transaction)
        Frequency.CUSTOM -> CustomTransaction(transaction)
    }
}

fun RecurrenceRequest.toDomain() = Recurrence(
    frequency = frequency,
    interval = day,
    times = count,
    workday = workDay
)

fun RemoteTransactionMetadata.toDomain(id: String) = TransactionMetadata(
    id = id,
    name = name,
    description = description,
    entry = entry.toLocalDate(),
    conclusion = conclusion.toLocalDate(),
    value = value,
    recurrence = Recurrence.parse(recurrence)
)

fun TransactionMetadata.toRemote(owner: String) = RemoteTransactionMetadata(
    name = name,
    description = description,
    entry = BigDecimal(entry.inMilliseconds()),
    owner = owner,
    conclusion = BigDecimal(conclusion.inMilliseconds()),
    recurrence = recurrence.toString(),
    value = value
)

private fun LocalDate.toTimestamp(): Timestamp {
    return if (this == LocalDate.MAX) {
        Timestamp.MAX_VALUE
    } else {
        Timestamp.of(Date.from(atTime(0, 0).toInstant(ZoneOffset.ofHours(0))))
    }
}

private fun BigDecimal.toLocalDate() = Instant
    .ofEpochMilli(this.toLong())
    .atOffset(ZoneOffset.UTC)
    .toLocalDate()
