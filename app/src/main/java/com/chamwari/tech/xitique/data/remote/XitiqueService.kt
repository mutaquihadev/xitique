package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.EventsResponse

interface XitiqueService {
    suspend fun getEvents() : EventsResponse
}