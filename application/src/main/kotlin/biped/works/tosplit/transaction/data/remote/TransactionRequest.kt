package biped.works.tosplit.transaction.data.remote

import biped.works.tosplit.transaction.data.domain.Value
import java.time.LocalDate

data class TransactionRequest(
    val id: String?,
    val owner: String,
    val name: String,
    val description: String,
    val due: LocalDate,
    val value: Value,
    val recurrence: RemoteRecurrence
)

data class TransactionUpdateRequest(
    val transaction: TransactionRequest,
    val policy: String
)