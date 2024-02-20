package biped.works.tosplit.transaction.data.remote

import java.time.LocalDate

data class TransactionRequest(
    val owner: String,
    val name: String,
    val description: String,
    val due: LocalDate,
    val value: Value,
    val recurrence: RemoteRecurrence
)

data class Value(val amount: Double, val currency: String)