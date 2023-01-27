package biped.works.tosplit.transaction.data

data class Recurrence(
    val frequency: Frequency = Frequency.CUSTOM,
    val day: Int = 10,
    val count: Int = -1,
    val workDay: Boolean = false
) {

    val isIndeterminate = count == -1

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("frequency=$frequency;")
        builder.append("day=$day;")
        builder.append("count=$count;")
        builder.append("workday=$workDay")

        return builder.toString()
    }
}

enum class Frequency {
    YEAR,
    MONTH,
    CUSTOM
}