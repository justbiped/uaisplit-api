package com.biped.tosplit.operation.data

import java.math.BigDecimal
import java.time.LocalDate

interface Operation {
    val id: String
    val name: String
    val description: String
    val entry: LocalDate
    val value: BigDecimal
    val recurrence: Recurrence

    fun generateMetadata(): List<OperationMetadata>
}

data class StandardOperation(
    override val id: String = "",
    override val name: String = "",
    override val description: String = "",
    override val entry: LocalDate = LocalDate.now(),
    override val value: BigDecimal = BigDecimal(0),
    override val recurrence: Recurrence = Recurrence()
) : Operation {
    override fun generateMetadata() = emptyList<OperationMetadata>()
}

data class MonthOperation(
    private val operation: Operation = StandardOperation()
) : Operation by operation {

    override fun generateMetadata(): List<OperationMetadata> {
        val recurrence = operation.recurrence
        val outDate = if (recurrence.isIndeterminate) {
            LocalDate.MAX
        } else {
            operation.entry
                .apply { plusMonths(recurrence.count - 1L) }
                .apply { withDayOfMonth(lengthOfMonth()) }
        }

        return listOf(
            OperationMetadata(
                id = operation.id,
                name = operation.name,
                description = operation.description,
                entry = operation.entry,
                conclusion = outDate,
                value = operation.value,
                recurrence = operation.recurrence.toString()
            )
        )
    }
}

data class YearOperation(
    private val operation: Operation = StandardOperation()
) : Operation by operation {

    override fun generateMetadata(): List<OperationMetadata> {
        val recurrence = operation.recurrence
        val outDate = if (recurrence.isIndeterminate) {
            LocalDate.MAX
        } else {
            operation.entry
                .apply { plusMonths(recurrence.count - 1L) }
                .apply { withDayOfMonth(lengthOfMonth()) }
        }

        return listOf(
            OperationMetadata(
                id = operation.id,
                name = operation.name,
                description = operation.description,
                entry = operation.entry,
                conclusion = outDate,
                value = operation.value,
                recurrence = operation.recurrence.toString()
            )
        )
    }
}

data class CustomOperation(
    private val operation: Operation = StandardOperation()
) : Operation by operation {

    override fun generateMetadata(): List<OperationMetadata> {
        val recurrence = operation.recurrence
        val outDate = if (recurrence.isIndeterminate) {
            LocalDate.MAX
        } else {
            operation.entry
                .apply { plusMonths(recurrence.count - 1L) }
                .apply { withDayOfMonth(lengthOfMonth()) }
        }

        return listOf(
            OperationMetadata(
                id = operation.id,
                name = operation.name,
                description = operation.description,
                entry = operation.entry,
                conclusion = outDate,
                value = operation.value,
                recurrence = operation.recurrence.toString()
            )
        )
    }
}

data class OperationMetadata(
    val id: String,
    val name: String,
    val description: String,
    val entry: LocalDate,
    val conclusion: LocalDate,
    val value: BigDecimal,
    val recurrence: String
)
//data class Reccurence(val frequency: ){
//frequency=month;day=16;workday=true;count=3
//frequency=year;day=16;workday=true;count=3
//frequency=custom;day=2;workday=true;count=3
//}

// custom range só pode aceitar de 29 em 29 dias max

//data class YearRecurrence(
//    override val count: Int,
//    override val day: Int,
//    override val workDay: Boolean
//) : Recurrence {
//    //Já cria todos os eventos anuais de uma vez pro mes do issue (max 30 anos) no banco de dados
//    override val frequency: Frequency = Frequency.YEAR
//}
//
//data class MonthRecurrence(
//    override val count: Int,
//    override val day: Int,
//    override val workDay: Boolean
//) : Recurrence {
//    override val frequency: Frequency = Frequency.MONTH
//}
//
//data class CustomRecurrence(
//    override val count: Int,
//    override val day: Int,
//    override val workDay: Boolean
//    //frequency=custom;day=2;workday=true;count=3
//    // cria os dias a partir do dia do issue (independente do mes)
//) : Recurrence {
//    override val frequency: Frequency = Frequency.CUSTOM
//}