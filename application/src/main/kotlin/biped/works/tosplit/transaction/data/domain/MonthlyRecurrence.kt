package biped.works.tosplit.transaction.data.domain

import biped.works.tosplit.core.DateTools
import biped.works.tosplit.core.isBeforeOrEquals
import java.time.LocalDate

data class MonthlyRecurrence(private val recurrence: Recurrence) : Recurrence by recurrence {

    override val conclusion: LocalDate = calculateConclusion()

    override fun generateDueDates(timeSpan: TimeSpan): List<LocalDate> {
        var dueDate = nextDueDate(timeSpan)
        val endDate = DateTools.min(conclusion, timeSpan.end)
        val dueDates = mutableListOf<LocalDate>()

        while (dueDate.isBeforeOrEquals(endDate)) {
            dueDates.add(dueDate)
            dueDate = dueDate.plusMonths(1)
        }
        return dueDates
    }

    private fun nextDueDate(timeSpan: TimeSpan): LocalDate {
        val startDay = start.dayOfMonth
        return if (timeSpan.start.isBeforeOrEquals(start)) {
            start
        } else {
            val dueDate = if (timeSpan.startDay > startDay) timeSpan.start.plusMonths(1) else timeSpan.start
            return dueDate.withAdjustableDayOfMonth(startDay)
        }
    }

    private fun calculateConclusion(): LocalDate {
        val frequency = parseFrequency()
        val conclusion = start
        return when {
            frequency.times < 1L -> LocalDate.MAX
            else -> conclusion.plusMonths(frequency.times - 1)
        }
    }

    private fun parseFrequency(): Frequency {
        val monthFrequency = Regex("times=(?<times>-?[0-9])+")
        val (timesText) = monthFrequency.find(frequency)?.destructured ?: throw Exception("")
        return Frequency(times = timesText.toLong(), false)
    }

    private data class Frequency(val times: Long, private val workday: Boolean)
}