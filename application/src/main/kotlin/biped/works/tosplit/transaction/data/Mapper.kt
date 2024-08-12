package biped.works.tosplit.transaction.data

import biped.works.tosplit.core.toEpochSecond
import biped.works.tosplit.transaction.data.domain.*
import biped.works.tosplit.transaction.data.remote.RemoteRecurrence
import biped.works.tosplit.transaction.data.remote.TransactionMetadataStore
import biped.works.tosplit.transaction.data.remote.TransactionRequest
import com.google.cloud.Timestamp
import java.time.LocalDate
import java.time.ZoneId

fun TransactionMetadataStore.toDomain(id: String) = TransactionMetadata(
    id = id,
    owner = owner,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence.toDomain(start)
)

fun TransactionMetadata.toRemote() = TransactionMetadataStore(
    name = name,
    description = description,
    start = start.toTimestamp(),
    conclusion = if (conclusion == LocalDate.MAX) Timestamp.MAX_VALUE else conclusion.toTimestamp(),
    owner = owner,
    recurrence = recurrence.toRemote(),
    value = value,
)

fun TransactionRequest.toDomain() = Transaction(
    id = id ?: "",
    owner = owner,
    name = name,
    description = description,
    due = due,
    value = value,
    recurrence = recurrence.toDomain(due)
)

private fun RemoteRecurrence.toDomain(start: Timestamp): Recurrence {
    val recurrenceType = RemoteRecurrence.Type.valueOf(type)
    val recurrence = recurrence(
        start = start.toLocalDate(),
        frequency = frequency,
    )

    return createRecurrence(recurrenceType, recurrence)
}

fun RemoteRecurrence.toDomain(due: LocalDate): Recurrence {
    val recurrenceType = RemoteRecurrence.Type.valueOf(type)
    val recurrence = recurrence(start = due, frequency = frequency)

    return createRecurrence(recurrenceType, recurrence)
}

private fun createRecurrence(
    recurrenceType: RemoteRecurrence.Type,
    recurrence: Recurrence
): Recurrence {
    return when (recurrenceType) {
        RemoteRecurrence.Type.MONTHLY -> MonthlyRecurrence(recurrence)
        else -> throw Exception("Recurrence not supported")
    }
}

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

fun LocalDate.toTimestamp(): Timestamp {
    return Timestamp.ofTimeSecondsAndNanos(toEpochSecond(), 0)
}
