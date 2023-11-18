package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RemoteOperationMetadata
import com.google.cloud.firestore.Firestore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class OperationRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getOperationMetadataList(range: Range): List<OperationMetadata> {
        val apiFuture = collection
            .whereEqualTo("owner", "aXTh7D9qGSNk1zjWtDrR")
            .whereGreaterThanOrEqualTo("entry", range.entry.inMilliseconds())
         //   .whereLessThan("conclusion", range.conclusion.inMilliseconds())
            .get()

        return apiFuture.get().documents.map { document ->
            document.toObject(RemoteOperationMetadata::class.java).toDomain(document.id)
        }
    }

    fun saveMetadata(operationMetadata: List<OperationMetadata>) {
        operationMetadata
            .map { it.toRemote("aXTh7D9qGSNk1zjWtDrR") }
            .forEach { collection.add(it) }
    }
}

fun LocalDate.inMilliseconds(offset: ZoneOffset = ZoneOffset.UTC): Long {
    return LocalDateTime
        .of(this, LocalTime.of(0, 0))
        .toEpochSecond(offset)
        .milliseconds
        .inWholeMilliseconds
}