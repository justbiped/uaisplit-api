package biped.works.tosplit.transaction.data.remote

import java.math.BigDecimal
import java.time.LocalDate

data class TransactionRequest(
    val id: String,
    val owner: String,
    val metaId: String,
    val name: String,
    val description: String,
    val due: LocalDate,
    val value: BigDecimal,
    val recurrence: RemoteRecurrence
)