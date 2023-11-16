package biped.works.tosplit.transaction.data.remote

import biped.works.tosplit.transaction.data.Frequency
import java.math.BigDecimal
import java.time.LocalDate

class TransactionRequest(
    val id: String,
    val name: String,
    val description: String,
    val entry: LocalDate,
    val recurrence: RecurrenceRequest,
    val value: BigDecimal
)

data class RecurrenceRequest(
    val frequency: Frequency = Frequency.CUSTOM,
    val day: Int = 10,
    val count: Int = -1,
    val workDay: Boolean = false
)
