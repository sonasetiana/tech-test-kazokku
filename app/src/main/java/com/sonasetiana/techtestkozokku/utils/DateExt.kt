package com.sonasetiana.techtestkozokku.utils

import java.text.SimpleDateFormat
import java.util.*

object DateExt {
    fun convertDate(dateTime: String): Date? {
        return try {
            val pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS"
            SimpleDateFormat(pattern, Locale.getDefault()).parse(dateTime)
        } catch (e: Exception) {
            null
        }
    }

    fun parseDate(dateTime: String?): String {
        if (dateTime == null) return ""
        val pattern = "dd MMMM yyyy HH:mm:ss"
        val date = convertDate(dateTime)
        return if (date != null) {
            SimpleDateFormat(pattern, Locale.getDefault()).format(date)
        } else {
            ""
        }
    }
}