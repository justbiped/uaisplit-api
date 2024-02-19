package biped.works.tosplit.transaction.data

import biped.works.tosplit.core.toEpochSecond
import biped.works.tosplit.core.toObject
import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import javax.inject.Inject

class TransactionRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getTransactionMetadataList(timeSpan: TimeSpan): List<TransactionMetadata> {
        val transactionMetadataQuery = collection
            .whereEqualTo("owner", "aXTh7D9qGSNk1zjWtDrR")
            .whereGreaterThanOrEqualTo("start", timeSpan.start.toEpochSecond())
            .get()

        return transactionMetadataQuery.get().documents
            .map { document -> parseDocument(document) }
            .filter { timeSpan.end.isBeforeOrEquals(it.conclusion) }
    }

    private fun parseDocument(document: QueryDocumentSnapshot) = document
        .toObject<RemoteTransactionMetadata>()
        .toDomain(document.id)

    fun saveTransactionMetadata(transactionMetadata: TransactionMetadata) {
        val remoteMetadata = transactionMetadata.toRemote()
        collection.document()
            .set(remoteMetadata)
            .get()
            .updateTime
    }
}
