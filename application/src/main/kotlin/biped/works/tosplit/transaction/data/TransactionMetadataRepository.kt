package biped.works.tosplit.transaction.data

import com.google.cloud.Timestamp
import com.google.firebase.cloud.FirestoreClient
import java.math.BigDecimal
import java.time.LocalDate
import javax.inject.Inject

class TransactionMetadataRepository @Inject constructor() {

    fun bla(): List<OperationMetadata> {
        //receive a date
        val db = FirestoreClient.getFirestore()
        val apiFuture = db.collection("transaction")
            .whereEqualTo("user", "aXTh7D9qGSNk1zjWtDrR")
            //            .whereGreaterThan("issue")
            //            .whereLessThan()
            .get()


        return apiFuture.get().documents.map { document ->
            val metadataEntity = document.toObject(OperationMetadataEntity::class.java)
            OperationMetadata(
                id = document.id,
                name = metadataEntity.name,
                description = metadataEntity.description,
                entry = metadataEntity.entry.toLocalDate(),
                conclusion = metadataEntity.conclusion.toLocalDate(),
                value = metadataEntity.value,
                recurrence = metadataEntity.recurrence
            )
        }
    }

    fun saveMetadata(operationMetadata: List<OperationMetadata>) {
        //saving metadata
    }
}

fun Timestamp.toLocalDate() = LocalDate.from(toDate().toInstant())

data class OperationMetadataEntity(
    val name: String = "",
    val description: String = "",
    val entry: Timestamp = Timestamp.now(),
    val conclusion: Timestamp = Timestamp.now(),
    val recurrence: String = "",
    val value: BigDecimal = BigDecimal(0.0)
)