package com.expensivebelly.paginglibrarywithrxjava

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

val nowPlus5MinutesFormatted
    get() = now().plusMinutes(5).format(journeyFormatter())

private fun now() = OffsetDateTime.now(ZoneId.systemDefault())
private fun journeyFormatter() =
    DateTimeFormatter.ofPattern("HHmm").withZone(ZoneId.systemDefault())