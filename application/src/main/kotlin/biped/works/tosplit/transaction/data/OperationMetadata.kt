package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class OperationMetadata(
    val id: String,
    val name: String,
    val description: String,
    val entry: LocalDate,
    val conclusion: LocalDate,
    val value: BigDecimal,
    val recurrence: Recurrence
) {
    fun createOperations(conclusion: LocalDate = LocalDate.MAX): List<Operation> = when (recurrence.frequency) {
        Frequency.MONTH -> createMontOperations()
        Frequency.CUSTOM -> createCustomOperations(conclusion)
        else -> emptyList()
    }

    private fun createCustomOperations(conclusion: LocalDate): List<Operation> {
        val endDate = conclusion
        var duty = entry

        val operations = mutableListOf<Operation>()

        while (duty.isBefore(endDate)) {
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

    private fun createMontOperations(): List<Operation> {
        return listOf(
            Operation(
                id = UUID.randomUUID().toString(),
                metaId = id,
                name = name,
                description = description,
                duty = entry,
                value = value
            )
        )
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