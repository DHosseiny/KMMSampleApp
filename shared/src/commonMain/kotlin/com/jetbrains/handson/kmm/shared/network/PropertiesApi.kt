package com.jetbrains.handson.kmm.shared.network

import com.jetbrains.handson.kmm.shared.entity.Properties
import io.ktor.client.*
import io.ktor.client.request.*
import me.tatarka.inject.annotations.Inject

@Inject
class PropertiesApi(private val httpClient: HttpClient) {

    // Http post example: SAMPLE_BODY used for simplicity but we can pass request models here
    suspend fun getAllProperties(): Properties {
        return httpClient.request("properties/v2/list") {
            setBody(SAMPLE_BODY)
        }
    }
}

private const val SAMPLE_BODY: String = """{
    "currency": "USD",
"eapid": 1,
"locale": "en_US",
"siteId": 300000001,
"destination": {
"regionId": "6054439"
},
"checkInDate": {
"day": 10,
"month": 10,
"year": 2022
},
"checkOutDate": {
"day": 15,
"month": 10,
"year": 2022
},
"rooms": [
{
    "adults": 2,
    "children": [
    {
        "age": 5
    },
    {
        "age": 7
    }
    ]
}
],
"resultsStartingIndex": 0,
"resultsSize": 200,
"sort": "PRICE_LOW_TO_HIGH",
"filters": {
"price": {
"max": 150,
"min": 100
}
}
}"""