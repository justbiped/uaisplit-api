package biped.works.tosplit.core

import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.gson.Gson

val gson = Gson()

inline fun <reified T> QueryDocumentSnapshot.toObject(): T {
    val json = gson.toJson(data)
    return gson.fromJson(json, T::class.java)
}

inline fun <reified T> DocumentSnapshot.toObject(): T {

    val json = gson.toJson(data)
    return gson.fromJson(json, T::class.java)
}