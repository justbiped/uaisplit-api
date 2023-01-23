package com.biped.tosplit.operation

import com.google.firebase.cloud.FirestoreClient

class OperationRepository {

    fun bla(): List<OperationMetadataEntity> {
        //receive a date
        val db = FirestoreClient.getFirestore()
        val query = db.collection("transaction")
            .whereEqualTo("user","aXTh7D9qGSNk1zjWtDrR")
//            .whereGreaterThan("issue")
//            .whereLessThan()
            .get()

        return query
            .get().documents.map {
            OperationMetadataEntity(
                id = it.id,
                name = it.getString("name").orEmpty(),
                description = it.getString("description").orEmpty(),
                issue = it.getTimestamp("issue").toString(),
                dutyEnd = it.getTimestamp("duty-end").toString(),
                recurrence = it.getString("recurrence").orEmpty(),
                value = it.getDouble("value") ?: 0.00
            )
        }
    }
}

data class OperationMetadataEntity(
    val id: String,
    val name: String,
    val description: String,
    val issue: String,
    val dutyEnd: String,
    val recurrence: String,
    val value: Number
)