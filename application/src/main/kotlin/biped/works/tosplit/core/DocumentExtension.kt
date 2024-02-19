package biped.works.tosplit.core

import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.gson.Gson

inline fun <reified T> QueryDocumentSnapshot.toObject(): T {
    val gson = Gson()
    val json = gson.toJson(data)
    return gson.fromJson(json, T::class.java)
}