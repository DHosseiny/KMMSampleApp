package com.jetbrains.handson.kmm.shared.network

import com.jetbrains.handson.kmm.shared.entity.RocketLaunchDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.tatarka.inject.annotations.Inject

@Inject
class LaunchesApi(private val httpClient: HttpClient) {

    // Http get example: because the base url is diferrent for our BASE_URL we should pass complete url
    suspend fun getAllLaunches(): List<RocketLaunchDto> {
        return httpClient.get("https://api.spacexdata.com/v5/launches").body()
    }
}