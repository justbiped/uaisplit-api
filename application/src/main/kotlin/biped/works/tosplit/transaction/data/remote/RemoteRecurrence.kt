package biped.works.tosplit.transaction.data.remote

data class RemoteRecurrence(
    val frequency: String,
    val type: String
) {
    enum class Type {
        YEARLY,
        MONTHLY,
        CUSTOM
    }
}