package biped.works.tosplit.transaction.data.domain

import biped.works.tosplit.core.isBeforeOrEquals
import java.time.LocalDate

interface Recurrence {
    val start: LocalDate
    val conclusion: LocalDate
    val frequency: String
    val startDay: Int get() = start.dayOfMonth

    fun generateDueDates(timeSpan: TimeSpan): List<LocalDate>
}

fun recurrence(
    start: LocalDate,
    frequency: String,
) = object : Recurrence {
    override val start: LocalDate = start
    override val frequency: String = frequency
    override val conclusion: LocalDate = LocalDate.MAX

    override fun generateDueDates(timeSpan: TimeSpan): List<LocalDate> = emptyList()
}

data class CustomRecurrence(private val recurrence: Recurrence) : Recurrence by recurrence {

    override fun generateDueDates(timeSpan: TimeSpan): List<LocalDate> {
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