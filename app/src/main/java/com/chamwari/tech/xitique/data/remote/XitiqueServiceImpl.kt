package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.UsersResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url

class XitiqueServiceImpl(private val client : HttpClient) : XitiqueService{
    override suspend fun getSignedUsers(): UsersResponse {
        return client.get {
            url(HttpRoutes.SIGNED_USERS)
        }
    }
}