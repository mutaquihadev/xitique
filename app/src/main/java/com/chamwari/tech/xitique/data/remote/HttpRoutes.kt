package com.chamwari.tech.xitique.data.remote

import com.chamwari.tech.xitique.BuildConfig


object HttpRoutes {
    private const val BASE_URL = BuildConfig.URL
    const val  SIGNED_USERS = "$BASE_URL/users"
    const val  MEMBERS = "$BASE_URL/member"
    const val  EVENTS = "$BASE_URL/events"
}