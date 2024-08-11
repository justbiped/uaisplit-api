package biped.works.tosplit.transaction.data

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class TransactionLocator(val key: String) {
    val metaId: String
    val due: LocalDate

    constructor(metaId: String, due: LocalDate) : this("${due.format()}$metaId")

    init {
        val (due, meta) = matchDueDateMetaIdPattern().destructured
        val dueDate = parseDueDate(due)
        this.due = dueDate
        this.metaId = meta
    }

    private fun matchDueDateMetaIdPattern(): MatchResult {
        val dueDateMetaIdRegex = Regex("(?<due>[0-9]{8})(?<meta>.+)")
        return dueDateMetaIdRegex.find(key)
            ?: throw IllegalArgumentException("The id value does not match with <yyyyMMdd><any char>")
    }

    private fun parseDueDate(due: String): LocalDate =
        LocalDate.parse(due, DateTimeFormatter.ofPattern("yyyyMMdd"))

    companion object {
        private fun LocalDate.format() = toString().replace("-", "")
    }
}