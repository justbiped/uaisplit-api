package biped.works.tosplit

import biped.works.tosplit.transaction.data.domain.*
import java.time.LocalDate

fun transactionMetadata(
    id: String = "",
    owner: String = "",
    name: String = "",
    description: String = "",
    value: Value = Value(22.00, "USD"),
    recurrence: Recurrence = monthlyRecurrence()

) = TransactionMetadata(
    id = id,
    owner = owner,
    name = name,
    description = description,
    value = value,
    recurrence = recurrence,
)

fun monthlyRecurrence(
    start: LocalDate = LocalDate.of(2024, 1, 20),
    frequency: String = "times=1"
) = MonthlyRecurrence(
    recurrence(start = start, frequency = frequency)
)