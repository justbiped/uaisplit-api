package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate

interface Transaction {
    val id: String
    val metaId: String
    val name: String
    val description: String
    val due: LocalDate
    val value: BigDecimal
    val recurrence: Recurrence

    fun toMetadata(): List<TransactionMetadata>
}

fun transaction(
    id: String = "",
    metaId: String = "",
    name: String = "",
    description: String = "",
    due: LocalDate = LocalDate.now(),
    value: BigDecimal = BigDecimal(0),
    recurrence: Recurrence = Recurrence(
        LocalDate.now(),
        LocalDate.MAX,
        Frequency(1, false),
        type = Recurrence.Type.CUSTOM
    )
) = object : Transaction {
    override val id: String = id
    override val metaId: String = metaId
    override val name: String = name
    override val description: String = description
    override val due: LocalDate = due
    override val value: BigDecimal = value
    override val recurrence: Recurrence = recurrence
    override fun toMetadata() = emptyList<TransactionMetadata>()
}
//
//data class MonthTransaction(
//    private val transaction: Transaction = transaction()
//) : Transaction by transaction {
//
//    override fun toMetadata(): List<TransactionMetadata> {
//        val recurrence = transaction.recurrence
//        val outDate = if (recurrence.isIndeterminate) {
//            LocalDate.MAX
//        } else {
//            transaction.due
//                .apply { plusMonths(recurrence.times - 1L) }
//                .apply { withDayOfMonth(lengthOfMonth()) }
//        }
//
//        return listOf(
//            TransactionMetadata(
//                id = transaction.id,
//                name = transaction.name,
//                description = transaction.description,
//                start = transaction.due,
//                conclusion = outDate,
//                value = transaction.value,
//                recurrence = transaction.recurrence
//            )
//        )
//    }
//}
//
//data class YearTransaction(
//    private val transaction: Transaction = transaction()
//) : Transaction by transaction {
//
//    override fun toMetadata(): List<TransactionMetadata> {
//        val recurrence = transaction.recurrence
//        val outDate = if (recurrence.isIndeterminate) {
//            LocalDate.MAX
//        } else {
//            transaction.due
//                .apply { plusMonths(recurrence.times - 1L) }
//                .apply { withDayOfMonth(lengthOfMonth()) }
//        }
//
//        return listOf(
//            TransactionMetadata(
//                id = transaction.id,
//                name = transaction.name,
//                description = transaction.description,
//                start = transaction.due,
//                conclusion = outDate,
//                value = transaction.value,
//                recurrence = transaction.recurrence
//            )
//        )
//    }
//}
//
//data class CustomTransaction(
//    private val transaction: Transaction = transaction()
//) : Transaction by transaction {
//
//    override fun toMetadata(): List<TransactionMetadata> {
//        val recurrence = transaction.recurrence
//        val outDate = if (recurrence.isIndeterminate) {
//            LocalDate.MAX
//        } else {
//            transaction.due
//                .apply { plusMonths(recurrence.times - 1L) }
//                .apply { withDayOfMonth(lengthOfMonth()) }
//        }
//
//        return listOf(
//            TransactionMetadata(
//                id = transaction.id,
//                name = transaction.name,
//                description = transaction.description,
//                start = transaction.due,
//                conclusion = outDate,
//                value = transaction.value,
//                recurrence = transaction.recurrence
//            )
//        )
//    }
//}

//data class Reccurence(val frequency: ){
//frequency=month;day=16;workday=true;count=3
//frequency=year;day=16;workday=true;count=3
//frequency=custom;day=2;workday=true;count=3
//}

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