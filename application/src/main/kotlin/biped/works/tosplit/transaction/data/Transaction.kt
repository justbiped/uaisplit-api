package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate

interface Transaction {
    val id: String
    val name: String
    val description: String
    val entry: LocalDate
    val value: BigDecimal
    val recurrence: Recurrence

    fun generateMetadata(): List<OperationMetadata>
}

data class StandardTransaction(
    override val id: String = "",
    override val name: String = "",
    override val description: String = "",
    override val entry: LocalDate = LocalDate.now(),
    override val value: BigDecimal = BigDecimal(0),
    override val recurrence: Recurrence = Recurrence()
) : Transaction {
    override fun generateMetadata() = emptyList<OperationMetadata>()
}

data class MonthTransaction(
    private val transaction: Transaction = StandardTransaction()
) : Transaction by transaction {

    override fun generateMetadata(): List<OperationMetadata> {
        val recurrence = transaction.recurrence
        val outDate = if (recurrence.isIndeterminate) {
            LocalDate.MAX
        } else {
            transaction.entry
                .apply { plusMonths(recurrence.count - 1L) }
                .apply { withDayOfMonth(lengthOfMonth()) }
        }

        return listOf(
            OperationMetadata(
                id = transaction.id,
                name = transaction.name,
                description = transaction.description,
                entry = transaction.entry,
                conclusion = outDate,
                value = transaction.value,
                recurrence = transaction.recurrence.toString()
            )
        )
    }
}

data class YearTransaction(
    private val transaction: Transaction = StandardTransaction()
) : Transaction by transaction {

    override fun generateMetadata(): List<OperationMetadata> {
        val recurrence = transaction.recurrence
        val outDate = if (recurrence.isIndeterminate) {
            LocalDate.MAX
        } else {
            transaction.entry
                .apply { plusMonths(recurrence.count - 1L) }
                .apply { withDayOfMonth(lengthOfMonth()) }
        }

        return listOf(
            OperationMetadata(
                id = transaction.id,
                name = transaction.name,
                description = transaction.description,
                entry = transaction.entry,
                conclusion = outDate,
                value = transaction.value,
                recurrence = transaction.recurrence.toString()
            )
        )
    }
}

data class CustomTransaction(
    private val transaction: Transaction = StandardTransaction()
) : Transaction by transaction {

    override fun generateMetadata(): List<OperationMetadata> {
        val recurrence = transaction.recurrence
        val outDate = if (recurrence.isIndeterminate) {
            LocalDate.MAX
        } else {
            transaction.entry
                .apply { plusMonths(recurrence.count - 1L) }
                .apply { withDayOfMonth(lengthOfMonth()) }
        }

        return listOf(
            OperationMetadata(
                id = transaction.id,
                name = transaction.name,
                description = transaction.description,
                entry = transaction.entry,
                conclusion = outDate,
                value = transaction.value,
                recurrence = transaction.recurrence.toString()
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