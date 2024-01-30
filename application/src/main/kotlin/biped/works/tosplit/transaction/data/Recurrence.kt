package biped.works.tosplit.transaction.data

import java.time.LocalDate

interface Recurrence {
    val start: LocalDate
    val conclusion: LocalDate
    val frequency: String
    val startDay: Int get() = start.dayOfMonth

    fun generateDupDates(timeSpan: TimeSpan): List<LocalDate>
}

fun recurrence(
    start: LocalDate,
    conclusion: LocalDate,
    frequency: String,
) = object : Recurrence {
    override val start: LocalDate = start
    override val conclusion: LocalDate = conclusion
    override val frequency: String = frequency

    override fun generateDupDates(timeSpan: TimeSpan): List<LocalDate> = emptyList()
}

data class MonthlyRecurrence(private val recurrence: Recurrence) : Recurrence by recurrence {

    override fun generateDupDates(timeSpan: TimeSpan): List<LocalDate> {
        var dueDate = nextDueDate(timeSpan)
        val endDate = DateTools.min(conclusion, timeSpan.end)
        val dueDates = mutableListOf<LocalDate>()

        while (dueDate.isBeforeOrEquals(endDate)) {
            dueDates.add(dueDate)
            dueDate = dueDate.plusMonths(1)
        }
        return dueDates
    }

    private  fun nextDueDate(timeSpan: TimeSpan): LocalDate {
        val startDay = start.dayOfMonth
        return if (timeSpan.start.isBeforeOrEquals(start)) {
            start
        } else {
            val dueDate = if (timeSpan.startDay > startDay) timeSpan.start.plusMonths(1) else timeSpan.start
            return dueDate.withAdjustableDayOfMonth(startDay)
        }
    }

    private fun parseFrequency(): Frequency {
        val regex = Regex("times=(?<times>-?[0-9]+);workday=(?<workday>true|false)")
        val match = regex.find(frequency) ?: throw Exception("Cant parse recurrence for value $frequency")

        val (times, workday) = match.destructured
        return Frequency(times.toInt(), workday.toBoolean())
    }

    private data class Frequency(private val times: Int, private val workday: Boolean)
}

data class CustomRecurrence(private val recurrence: Recurrence) : Recurrence by recurrence {

    override fun generateDupDates(timeSpan: TimeSpan): List<LocalDate> {
        var due = getDueDate(timeSpan)
        val dueDates = mutableListOf<LocalDate>()

        while (due.isBeforeOrEquals(timeSpan.end)) {
            dueDates.add(due)
            due = due.plusDays(7)
        }
        return dueDates
    }

    private fun getDueDate(timeSpan: TimeSpan): LocalDate {
        return if (timeSpan.start.isBeforeOrEquals(start)) start else createDueDate(timeSpan)
    }

    private fun createDueDate(timeSpan: TimeSpan): LocalDate {
        val dueDate = if (timeSpan.startDay > startDay) timeSpan.start.plusMonths(1) else timeSpan.start
        return dueDate.withAdjustableDayOfMonth(startDay)
    }
}