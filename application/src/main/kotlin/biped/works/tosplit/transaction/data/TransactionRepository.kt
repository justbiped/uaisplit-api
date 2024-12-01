package biped.works.tosplit.transaction.data

import biped.works.tosplit.core.toObject
import biped.works.tosplit.transaction.data.domain.TimeSpan
import biped.works.tosplit.transaction.data.domain.TransactionMetadata
import biped.works.tosplit.transaction.data.remote.TransactionMetadataStore
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TransactionRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getTransactionMetadataList(timeSpan: TimeSpan): List<TransactionMetadata> {
        val transactionMetadataQuery = collection
            .whereEqualTo("owner", "aXTh7D9qGSNk1zjWtDrR")
            .get()

        return transactionMetadataQuery.get().documents
            .map { document -> parseDocument(document) }
            .filter { meta -> hasIntersection(meta, timeSpan) }
    }

    private fun hasIntersection(meta: TransactionMetadata, second: TimeSpan): Boolean {
        return meta.start <= second.start && meta.conclusion >= meta.start
                || second.start <= meta.start && second.end >= meta.start
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

    fun saveTransactionMetadata(transactionMetadata: TransactionMetadata): String {
        val remoteMetadata = transactionMetadata.toRemote()
        val document = if (transactionMetadata.id.isBlank()) {
            collection.document()
        } else {
            collection.document(transactionMetadata.id)
        }

        val id = runBlocking {
            suspendCoroutine<String> { continuation ->
                document
                    .addSnapshotListener { documentSnapshot, exception ->
                        if (exception != null) continuation.resumeWithException(exception)

                        val id = documentSnapshot?.id
                        if (id != null) continuation.resume(id)
                        else continuation.resumeWithException(Exception("Unable to retrieve meta id"))
                    }
            }
        }

        document.set(remoteMetadata).get()
        return id
    }
}
