package com.jetbrains.handson.kmm.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    @SerialName("data")
    val data: Data
)

@Serializable
data class Data(
    val propertySearch: PropertySearch
)

@Serializable
data class PropertySearch(
    @SerialName("__typename")
    val typename: String,
    @SerialName("properties")
    val properties: List<Property>
)

@Serializable
class Property(
    @SerialName("name")
    val name: String
)