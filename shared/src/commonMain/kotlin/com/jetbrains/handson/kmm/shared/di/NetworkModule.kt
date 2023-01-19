package com.jetbrains.handson.kmm.shared.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.api.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
interface NetworkModule {

    private val customHeaderPlugin
        get() = createClientPlugin("CustomHeaderPlugin") {
            onRequest { request, _ ->
                request.headers.append("X-davud-Header", "Davud")
            }
        }

    val httpClient: HttpClient
        @Provides
        get() = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
            install(customHeaderPlugin)
            defaultRequest {
                url(BASE_URL)
                headers {
                    append("X-RapidAPI-Key", "c15bb88730msh23aab24a369a853p124725jsn617622500e21")
                    append("X-RapidAPI-Host", "hotels4.p.rapidapi.com")
                }
                contentType(ContentType.Application.Json)
            }
        }.apply {
            plugin(HttpSend).intercept { request ->
                val originalCall = execute(request)
//            if (originalCall.response.status.value !in 100..399) {
//                execute(request)
//            } else {
                originalCall
//            }
            }
        }

    companion object {
        private const val BASE_URL = "https://hotels4.p.rapidapi.com/"
    }
}