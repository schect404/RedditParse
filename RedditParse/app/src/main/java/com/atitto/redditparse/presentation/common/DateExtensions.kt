package com.atitto.redditparse.presentation.common

import android.content.Context
import com.atitto.redditparse.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private val RECENTLY_RANGE = 0..59
private val ONE_MINUTE_RANGE = 60..61
private val MINUTES_RANGE = 62..3599
private val ONE_HOUR_RANGE = 3600..5399
private val HOURS_RANGE = 5400..86399
private val ONE_DAY_RANGE = 86400..172800
private val DAYS_RANGE = 172800..604800
private const val FORMAT_PATTERN = "yyyy MMM d HH:mm"

fun Long.toDateString(context: Context): String? {

    val timeNow: Long = TimeUnit.MILLISECONDS.toSeconds(Date().time)
    val minused = minus(timeNow)

    return when(minused) {
        in RECENTLY_RANGE -> R.string.recently.resolveResource(context)
        in ONE_MINUTE_RANGE -> R.string.minute_ago.resolveResource(context)
        in MINUTES_RANGE -> R.string.minutes_ago.resolveResource(context)?.format(TimeUnit.SECONDS.toMinutes(minused))
        in ONE_HOUR_RANGE -> R.string.hour_ago.resolveResource(context)
        in HOURS_RANGE -> R.string.hours_ago.resolveResource(context)?.format(TimeUnit.SECONDS.toHours(minused))
        in ONE_DAY_RANGE -> R.string.day_ago.resolveResource(context)
        in DAYS_RANGE -> R.string.days_ago.resolveResource(context)?.format(TimeUnit.SECONDS.toDays(minused))
        else -> SimpleDateFormat(FORMAT_PATTERN, Locale.getDefault()).format(Date(toMillis()))
    }

}

fun Long.toMillis() = TimeUnit.SECONDS.toMillis(this)

fun Int.resolveResource(context: Context): String? = context.resources.getString(this)
