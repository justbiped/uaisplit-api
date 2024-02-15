package biped.works.tosplit.transaction.data.remote

import java.math.BigDecimal

data class RemoteTransactionMetadata(
    val name: String,
    val description: String,
    val start: BigDecimal,
    val conclusion: BigDecimal,
    val owner: String,
    val recurrence: RemoteRecurrence,
    val value: BigDecimal
)

data class RemoteRecurrence(
    val start: String,
    val conclusion: String,
    val frequency: String,
    val type: String
) {
    enum class Type {
        YEARLY,
        MONTHLY,
        CUSTOM
    }
}


