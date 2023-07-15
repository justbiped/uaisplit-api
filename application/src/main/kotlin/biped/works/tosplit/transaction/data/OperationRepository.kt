package biped.works.tosplit.transaction.data

import com.google.cloud.firestore.Firestore
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class OperationRepository @Inject constructor(firestore: Firestore) {

    private val collection = firestore.collection("transaction")

    fun getOperationMetadataList(): List<OperationMetadata> {
        val apiFuture = collection
            .whereEqualTo("user", "aXTh7D9qGSNk1zjWtDrR")
            //            .whereGreaterThan("issue")
            //            .whereLessThan()
            .get()

        //        return apiFuture.get().documents.map { document ->
        //            document.toObject(RemoteOperationMetadata::class.java).toDomain(document.id)
        //        }

        return listOf(
            OperationMetadata(
                id = UUID.randomUUID().toString(),
                name = "Aluguel",
                description = "todo mes",
                entry = LocalDate.of(2023,7,15),
                conclusion = LocalDate.of(2023,12,15),
                value = BigDecimal.valueOf(123),
                recurrence = Recurrence(frequency = Frequency.MONTH, times = 5)
            ),
            OperationMetadata(
                id = UUID.randomUUID().toString(),
                name = "Drugs sell",
                description = "every day, ",
                entry = LocalDate.of(2023,1,1),
                conclusion = LocalDate.of(2023,1,30),
                value = BigDecimal.valueOf(123),
                recurrence = Recurrence(frequency = Frequency.CUSTOM, interval = 7)
            )
        )
    }

    fun saveMetadata(operationMetadata: List<OperationMetadata>) {
        operationMetadata
            .map { it.toEntity("aXTh7D9qGSNk1zjWtDrR") }
            .forEach { collection.add(it) }
    }
}