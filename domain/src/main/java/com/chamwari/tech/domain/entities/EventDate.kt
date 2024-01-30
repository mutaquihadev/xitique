package com.chamwari.tech.domain.entities

import java.lang.Exception
import java.util.Date

data class EventDate(private val timeStamp: Long)
{
    init {
        val (date, isValid) = try {
            Pair(Date(timeStamp), true)
        } catch(e: Exception) {
            Pair(null, false)
        }
        require(isValid)
    }
}