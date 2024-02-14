package biped.works.tosplit.transaction.data

import java.math.BigDecimal
import java.time.LocalDate

interface Transaction {
    val id: String
    val metaId: String
    val name: String
    val description: String
    val due: LocalDate
    val value: BigDecimal
    val recurrence: Recurrence
}

fun transaction(
    id: String = "",
    metaId: String = "",
    name: String = "",
    description: String = "",
    due: LocalDate = LocalDate.now(),
    value: BigDecimal = BigDecimal(0),
    recurrence: Recurrence = recurrence(LocalDate.now(), LocalDate.MAX, "")
) = object : Transaction {
    override val id: String = id
    override val metaId: String = metaId
    override val name: String = name
    override val description: String = description
    override val due: LocalDate = due
    override val value: BigDecimal = value
    override val recurrence: Recurrence = recurrence
}