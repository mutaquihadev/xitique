package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.data.remote.dto.UsersResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface XitiqueService {
    suspend fun getSignedUsers() : UsersResponse

    companion object {
        fun createXitiqueService() : XitiqueService {
            return XitiqueServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}