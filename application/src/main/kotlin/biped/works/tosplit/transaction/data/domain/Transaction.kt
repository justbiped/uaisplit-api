package biped.works.tosplit.transaction.data.domain

import java.time.LocalDate

data class Transaction(
    val id: String,
    val owner: String,
    val name: String,
    val description: String,
    val due: LocalDate,
    val value: Value,
    val recurrence: Recurrence,
)