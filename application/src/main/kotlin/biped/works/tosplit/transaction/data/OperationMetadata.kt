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
    fun createOperations(): List<Operation> {
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
