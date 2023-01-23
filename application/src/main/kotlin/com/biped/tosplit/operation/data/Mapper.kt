package com.biped.tosplit.operation.data

fun OperationRequest.toDomain(): Operation {
    val standardOperation = StandardOperation(
        id = id,
        name = name,
        description = description,
        entry = entry,
        value = value,
        recurrence = recurrence.toDomain()
    )

    return when (recurrence.frequency) {
        Frequency.YEAR -> YearOperation(standardOperation)
        Frequency.MONTH -> MonthOperation(standardOperation)
        Frequency.CUSTOM -> CustomOperation(standardOperation)
    }
}

fun RecurrenceRequest.toDomain() = Recurrence(
    frequency = frequency,
    day = day,
    count = count,
    workDay = workDay
)