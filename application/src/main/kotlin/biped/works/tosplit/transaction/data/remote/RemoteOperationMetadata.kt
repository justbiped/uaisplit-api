package biped.works.tosplit.transaction.data.remote

import com.google.cloud.Timestamp
import java.math.BigDecimal

data class RemoteOperationMetadata(
    val name: String = "",
    val description: String = "",
    val entry: Timestamp = Timestamp.now(),
    val conclusion: Timestamp = Timestamp.now(),
    val owner: String = "",
    val recurrence: String = "",
    val value: BigDecimal = BigDecimal(0.0)
)