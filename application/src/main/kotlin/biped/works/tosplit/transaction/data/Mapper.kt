package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RecurrenceRequest
import biped.works.tosplit.transaction.data.remote.RemoteOperationMetadata
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import com.google.cloud.Timestamp
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date

fun TransactionRequest.toDomain(): Transaction {
    val transaction = transaction(
        id = id,
        name = name,
        description = description,
        entry = entry,
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

fun RemoteOperationMetadata.toDomain(id: String) = OperationMetadata(
    id = id,
    name = name,
    description = description,
    entry = entry.toLocalDate(),
    conclusion = conclusion.toLocalDate(),
    value = value,
    recurrence = Recurrence.parse(recurrence)
)

fun OperationMetadata.toEntity(owner: String) = RemoteOperationMetadata(
    name = name,
    description = description,
    entry = entry.toTimestamp(),
    owner = owner,
    conclusion = conclusion.toTimestamp(),
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

private fun Timestamp.toLocalDate() = LocalDate.from(toDate().toInstant())
