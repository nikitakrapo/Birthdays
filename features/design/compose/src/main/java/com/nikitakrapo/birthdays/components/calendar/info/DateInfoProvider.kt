package com.nikitakrapo.birthdays.components.calendar.info

import kotlinx.datetime.LocalDate

/**
 * Provides all info for the specified LocalDate
 */
fun interface DateInfoProvider {

    fun getDateInfo(date: LocalDate): DateInfo

    companion object {
        fun empty() = DateInfoProvider { DateInfo(false) }
    }
}