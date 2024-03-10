package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.EventsResponse
import com.chamwari.tech.xitique.data.remote.dto.UsersResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class XitiqueServiceImpl(
    private val client : HttpClient) : XitiqueService{
    override suspend fun getSignedUsers(): UsersResponse {
        return client.get {
            url(HttpRoutes.SIGNED_USERS)
        }
    }

    override suspend fun getEvents(): EventsResponse {
        return client.get {
            url(HttpRoutes.EVENTS)
        }
    }
}