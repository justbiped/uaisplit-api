package biped.works.tosplit.transaction.data

fun TransactionRequest.toDomain(): Transaction {
    val standardOperation = StandardTransaction(
        id = id,
        name = name,
        description = description,
        entry = entry,
        value = value,
        recurrence = recurrence.toDomain()
    )

    return when (recurrence.frequency) {
        Frequency.YEAR -> YearTransaction(standardOperation)
        Frequency.MONTH -> MonthTransaction(standardOperation)
        Frequency.CUSTOM -> CustomTransaction(standardOperation)
    }
}

fun RecurrenceRequest.toDomain() = Recurrence(
    frequency = frequency,
    day = day,
    count = count,
    workDay = workDay
)