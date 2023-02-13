package biped.works.tosplit.transaction.data

import com.google.cloud.firestore.Firestore
import javax.inject.Inject

class TransactionMetadataRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getMetadata(): List<OperationMetadata> {
        val apiFuture = collection
            .whereEqualTo("user", "aXTh7D9qGSNk1zjWtDrR")
            //            .whereGreaterThan("issue")
            //            .whereLessThan()
            .get()

        return apiFuture.get().documents.map { document ->
            document.toObject(OperationMetadataEntity::class.java).toDomain(document.id)
        }
    }

    fun saveMetadata(operationMetadata: List<OperationMetadata>) {
        operationMetadata
            .map { it.toEntity("aXTh7D9qGSNk1zjWtDrR") }
            .forEach { collection.add(it) }
    }
}