package biped.works.tosplit.transaction.data

import biped.works.tosplit.transaction.data.remote.RemoteTransactionMetadata
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.gson.Gson
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class TransactionRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getTransactionMetadataList(timeSpan: TimeSpan): List<TransactionMetadata> {
        val apiFuture = collection
            .whereEqualTo("owner", "aXTh7D9qGSNk1zjWtDrR")
            .whereGreaterThanOrEqualTo("start", timeSpan.start.inMilliseconds())
            .get()

        return apiFuture.get().documents
            .map { document -> document.toObject<RemoteTransactionMetadata>().toDomain(document.id) }
            //.filter { it.recurrence.conclusion.isBeforeOrEquals(timeSpan.end) }
    }
}

inline fun <reified T> QueryDocumentSnapshot.toObject(): T {
    val gson = Gson()
    val json = gson.toJson(data)
    return gson.fromJson(json, T::class.java)
}

fun LocalDate.inMilliseconds(offset: ZoneOffset = ZoneOffset.UTC): Long {
    return LocalDateTime
        .of(this, LocalTime.of(0, 0))
        .toEpochSecond(offset)
        .milliseconds
        .inWholeMilliseconds
}