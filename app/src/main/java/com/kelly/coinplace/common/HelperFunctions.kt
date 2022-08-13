package com.kelly.coinplace.common

import java.text.ParseException
import java.text.SimpleDateFormat

fun getErrorMessage(): String {
    return Constants.ERROR_MESSAGE
}

fun String.parseStringToDateAndFormatToString(pattern: String): String {
    var result = ""
    val parseToDate = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
    val formatToString = SimpleDateFormat(pattern)
    try {
        result = formatToString.format(parseToDate.parse(this))
    } catch (p: ParseException) {
        p.printStackTrace()
    }
    return result
}