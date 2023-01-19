package com.jetbrains.handson.kmm.shared.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

suspend inline fun <reified T> HttpClient.request(
    relativePath: String,
    block: HttpRequestBuilder.() -> Unit = {}
) : T {
    return post(relativePath, block).body()
}