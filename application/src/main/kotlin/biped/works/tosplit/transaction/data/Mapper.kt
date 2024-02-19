package biped.works.tosplit.transaction.data

import biped.works.tosplit.core.toEpochSecond
import biped.works.tosplit.transaction.data.remote.RemoteRecurrence
import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import com.google.cloud.Timestamp
import java.time.LocalDate
import java.time.ZoneId

fun RemoteTransactionMetadata.toDomain(id: String) = TransactionMetadata(
    id = id,
    owner = owner,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence.toDomain(start, conclusion)
)

private fun RemoteRecurrence.toDomain(
    start: Timestamp,
    conclusion: Timestamp
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

fun TransactionMetadata.toRemote() = RemoteTransactionMetadata(
    name = name,
    description = description,
    start = start.toTimestamp(),
    conclusion = if (conclusion == LocalDate.MAX) Timestamp.MAX_VALUE else conclusion.toTimestamp(),
    owner = owner,
    recurrence = recurrence.toRemote(),
    value = value,
)

private fun Recurrence.toRemote(): RemoteRecurrence {
    val type = when (this) {
        is MonthlyRecurrence -> RemoteRecurrence.Type.MONTHLY
        else -> RemoteRecurrence.Type.CUSTOM
    }
    return RemoteRecurrence(
        frequency = frequency,
        type = type.toString()
    )
}

private fun Timestamp.toLocalDate(): LocalDate {
    return LocalDate.ofInstant(toDate().toInstant(), ZoneId.of("UTC"))
}

private fun LocalDate.toTimestamp(): Timestamp {
    return Timestamp.ofTimeSecondsAndNanos(toEpochSecond(), 0)
}
