package biped.works.tosplit.transaction.data

import java.time.LocalDate

data class Recurrence(
    val start: LocalDate = LocalDate.now(),
    val frequency: Frequency = Frequency.CUSTOM,
    val interval: Int = 7,
    val times: Int = -1,
    val workday: Boolean = false
) {

    val isIndeterminate = times == -1

    fun nextDueDate(timeSpan: TimeSpan): LocalDate {
        val startDay = start.dayOfMonth
        return if (timeSpan.start.isBeforeOrEquals(start)) {
            start
        } else {
            val dueDate = if (timeSpan.startDay > startDay) timeSpan.start.plusMonths(1) else timeSpan.start
            return dueDate.withAdjustableDayOfMonth(startDay)
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("start=$start;")
        builder.append("frequency=$frequency;")
        builder.append("day=$interval;")
        builder.append("times=$times;")
        builder.append("workday=$workday")

        return builder.toString()
    }

    companion object {
        private const val RECURRENCE_PATTERN =
            "start=(?<start>[0-9]{4}-[0-9]{2}-[0-9]{2});frequency=(?<frequency>[A-Z]+);day=(?<day>[0-9]+);times=(?<times>-?[0-9]+);workday=(?<workday>true|false)"

        fun parse(recurrence: String): Recurrence {
            val regex = Regex(RECURRENCE_PATTERN)
            val match = regex.find(recurrence) ?: throw Exception("Cant parse recurrence for value $recurrence")
            val (start, frequency, day, times, workday) = match.destructured

            return Recurrence(
                start = LocalDate.parse(start),
                frequency = Frequency.valueOf(frequency),
                interval = day.toInt(),
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