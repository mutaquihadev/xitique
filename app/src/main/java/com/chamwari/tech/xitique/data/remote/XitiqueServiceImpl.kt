package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.EventsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class XitiqueServiceImpl(
    private val client : HttpClient) : XitiqueService{
    override suspend fun getEvents(): EventsResponse {
        return client.get {
            url(HttpRoutes.EVENTS)
        }
    }
}

