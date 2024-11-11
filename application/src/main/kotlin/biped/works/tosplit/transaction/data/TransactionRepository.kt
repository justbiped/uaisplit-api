package biped.works.tosplit.transaction.data

import biped.works.tosplit.core.isBeforeOrEquals
import biped.works.tosplit.core.toObject
import biped.works.tosplit.transaction.data.domain.TimeSpan
import biped.works.tosplit.transaction.data.domain.TransactionMetadata
import biped.works.tosplit.transaction.data.remote.TransactionMetadataStore
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import javax.inject.Inject

class TransactionRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getTransactionMetadataList(timeSpan: TimeSpan): List<TransactionMetadata> {
        val transactionMetadataQuery = collection
            .whereEqualTo("owner", "aXTh7D9qGSNk1zjWtDrR")
            .get()

        return transactionMetadataQuery.get().documents
            .map { document -> parseDocument(document) }
            .filter {
                (it.start.isAfter(timeSpan.start) || it.start.isEqual(timeSpan.start))
                        && it.conclusion.isBeforeOrEquals(timeSpan.end)
            }
    }

    fun getTransactionMetadata(
        metaId: String
    ): TransactionMetadata {
        val metadataDocument = collection.document(metaId).get().get()
        return metadataDocument
            .toObject<TransactionMetadataStore>()
            .toDomain(metadataDocument.id)
    }

    private fun parseDocument(document: QueryDocumentSnapshot) = document
        .toObject<TransactionMetadataStore>()
        .toDomain(document.id)

    fun saveTransactionMetadata(transactionMetadata: TransactionMetadata) {
        val remoteMetadata = transactionMetadata.toRemote()
        val document = if (transactionMetadata.id.isBlank()) {
            collection.document()
        } else {
            collection.document(transactionMetadata.id)
        }

        document
            .set(remoteMetadata)
            .get()
    }
}
