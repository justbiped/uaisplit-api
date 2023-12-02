package biped.works.tosplit.transaction.data.remote

import java.math.BigDecimal

data class RemoteTransactionMetadata(
    val name: String = "",
    val description: String = "",
    val entry: BigDecimal = BigDecimal.ZERO,
    val conclusion: BigDecimal = BigDecimal.valueOf(Long.MAX_VALUE),
    val owner: String = "",
    val recurrence: String = "",
    val value: BigDecimal = BigDecimal(0.0)
)