package biped.works.tosplit.transaction.data

data class Recurrence(
    val frequency: Frequency = Frequency.CUSTOM,
    val day: Int = 10,
    val times: Int = -1,
    val workday: Boolean = false
) {

    val isIndeterminate = times == -1

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("frequency=$frequency;")
        builder.append("day=$day;")
        builder.append("times=$times;")
        builder.append("workday=$workday")

        return builder.toString()
    }

    companion object {
        private const val recurrencePattern =
            "frequency=(?<frequency>[A-Z]+);day=(?<day>[0-9]+);times=(?<times>[0-9]+);workday=(?<workday>true|false)"

        fun parse(recurrence: String): Recurrence {
            val regex = Regex(recurrencePattern)
            val match = regex.find(recurrence) ?: throw Exception("Cant parse recurrence for value $recurrence")
            val (frequency, day, times, workday) = match.destructured

            return Recurrence(
                frequency = Frequency.valueOf(frequency),
                day = day.toInt(),
                times = times.toInt(),
                workday = workday.toBoolean()
            )
        }
    }
}

enum class Frequency {
    YEAR,
    MONTH,
    CUSTOM
}