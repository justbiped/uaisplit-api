package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.util.UUID
import kotlin.math.min
import org.apache.tomcat.jni.Local

data class OperationMetadata(
    val id: String,
    val name: String,
    val description: String,
    val entry: LocalDate,
    val conclusion: LocalDate,
    val value: BigDecimal,
    val recurrence: Recurrence
) {
    fun createOperations(range: Range = Range()): List<Operation> = when (recurrence.frequency) {
        Frequency.MONTH -> createMontOperations(range)
        Frequency.CUSTOM -> createCustomOperations(range)
        else -> emptyList()
    }

    private fun createCustomOperations(range: Range): List<Operation> {
        var duty = entry
        val operations = mutableListOf<Operation>()

        while (duty.isBeforeOrEquals(range.conclusion)) {
            val operation = Operation(
                id = UUID.randomUUID().toString(),
                metaId = id,
                name = name,
                description = description,
                duty = duty,
                value = value
            )

            operations.add(operation)
            duty = duty.plusDays(recurrence.interval.toLong())
        }

        return operations
    }

    private fun createMontOperations(range: Range): List<Operation> {
        var duty = entry
        val endDate = DateTools.min(conclusion, range.conclusion)
        val operations = mutableListOf<Operation>()

        while (duty.isBeforeOrEquals(endDate)) {
            val operation = Operation(
                id = UUID.randomUUID().toString(),
                metaId = id,
                name = name,
                description = description,
                duty = duty,
                value = value
            )

            operations.add(operation)
            duty = duty.plusMonths(1)
        }

        return operations
    }

}

data class Operation(
    val id: String,
    val metaId: String,
    val name: String,
    val description: String,
    val duty: LocalDate,
    val value: BigDecimal
)

data class Range(
    val entry: LocalDate = LocalDate.MIN,
    val conclusion: LocalDate = LocalDate.MAX
)

object DateTools {
    fun min(first: LocalDate, second: LocalDate) = if (first.isBefore(second)) first else second
}

fun LocalDate.isBeforeOrEquals(other: ChronoLocalDate): Boolean {
    return isBefore(other) || this == other
}