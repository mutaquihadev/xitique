package com.chamwari.tech.xitique

import androidx.room.Room
import com.chamwari.tech.xitique.BuildConfig.TOKEN
import com.chamwari.tech.xitique.data.db.EventDAO
import com.chamwari.tech.xitique.data.db.XitiqueDatabase
import com.chamwari.tech.xitique.data.remote.XitiqueService
import com.chamwari.tech.xitique.data.remote.XitiqueServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private const val DB = "com.chamwari.tech.xitique.data.db.XitiqueDatabase"
val appModule = module {

    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(DefaultRequest) {
                header("Authorization", "Bearer $TOKEN")
            }
        }
    }

    single<EventDAO> {
        get<XitiqueDatabase>().eventDao()
    }

    single<XitiqueService> {
        XitiqueServiceImpl(client = get())
    }

    single<MainRepository> {
        MainRepositoryImpl(service = get(), eventDAO = get())
    }
    viewModel {
        MainViewModel(repository = get())
    }

    single {
        Room.databaseBuilder(get(), XitiqueDatabase::class.java, DB)
            .build()
    }

}