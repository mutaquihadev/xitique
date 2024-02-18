package com.chamwari.tech.xitique

import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.XitiqueServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    single<XitiqueService> {
        XitiqueServiceImpl(client = get())
    }

    single<MainRepository> {
        MainRepositoryImpl(service = get())
    }
    viewModel {
        MainViewModel(repository = get())
    }
}