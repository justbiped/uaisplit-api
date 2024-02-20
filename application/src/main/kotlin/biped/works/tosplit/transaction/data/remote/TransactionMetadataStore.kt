package biped.works.tosplit.transaction.data.remote

import biped.works.tosplit.transaction.data.domain.Value
import com.google.cloud.Timestamp

data class TransactionMetadataStore(
    val name: String,
    val description: String,
    val start: Timestamp,
    val conclusion: Timestamp,
    val owner: String,
    val recurrence: RemoteRecurrence,
    val value: Value
)


