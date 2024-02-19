package biped.works.tosplit.transaction.data.remote

import com.google.cloud.Timestamp
import java.math.BigDecimal

data class RemoteTransactionMetadata(
    val name: String,
    val description: String,
    val start: Timestamp,
    val conclusion: Timestamp,
    val owner: String,
    val recurrence: RemoteRecurrence,
    val value: BigDecimal
)

data class RemoteRecurrence(
    val frequency: String,
    val type: String
) {
    enum class Type {
        YEARLY,
        MONTHLY,
        CUSTOM
    }
}


