package biped.works.tosplit.transaction.data

import com.google.cloud.Timestamp
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date

fun TransactionRequest.toDomain(): Transaction {
    val standardOperation = StandardTransaction(
        id = id,
        name = name,
        description = description,
        entry = entry,
        value = value,
        recurrence = recurrence.toDomain()
    )

    return when (recurrence.frequency) {
        Frequency.YEAR -> YearTransaction(standardOperation)
        Frequency.MONTH -> MonthTransaction(standardOperation)
        Frequency.CUSTOM -> CustomTransaction(standardOperation)
    }
}

fun RecurrenceRequest.toDomain() = Recurrence(
    frequency = frequency,
    day = day,
    count = count,
    workDay = workDay
)

fun OperationMetadataEntity.toDomain(id: String) = OperationMetadata(
    id = id,
    name = name,
    description = description,
    entry = entry.toLocalDate(),
    conclusion = conclusion.toLocalDate(),
    value = value,
    recurrence = recurrence
)

fun OperationMetadata.toEntity(owner: String) = OperationMetadataEntity(
    name = name,
    description = description,
    entry = entry.toTimestamp(),
    owner = owner,
    conclusion = conclusion.toTimestamp(),
    recurrence = recurrence,
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
