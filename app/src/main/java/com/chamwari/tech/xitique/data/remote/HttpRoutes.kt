package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.BuildConfig


object HttpRoutes {
    private const val BASE_URL = BuildConfig.URL
    const val  EVENTS = "$BASE_URL/events"
}