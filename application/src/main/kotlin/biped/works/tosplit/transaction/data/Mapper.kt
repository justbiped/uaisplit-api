package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RemoteRecurrence
import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import org.apache.tomcat.jni.Local
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

fun RemoteTransactionMetadata.toDomain(id: String) = TransactionMetadata(
    id = id,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence.toDomain()
)

private fun RemoteRecurrence.toDomain(): Recurrence {
    val conclusion = if (conclusion.isEmpty()) LocalDate.MAX else LocalDate.parse(conclusion)
    val recurrenceType = RemoteRecurrence.Type.valueOf(type)
    val recurrence = recurrence(
        start = LocalDate.parse(start),
        conclusion = conclusion,
        frequency = frequency,
    )

    return when (recurrenceType) {
        RemoteRecurrence.Type.MONTHLY -> MonthlyRecurrence(recurrence)
        else -> throw Exception("Recurrence not supported")
    }
}

private fun BigDecimal.toLocalDate() = Instant
    .ofEpochMilli(this.toLong())
    .atOffset(ZoneOffset.UTC)
    .toLocalDate()
