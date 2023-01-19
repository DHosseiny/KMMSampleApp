package com.jetbrains.handson.kmm.shared

import com.jetbrains.handson.kmm.shared.entity.Property
import com.jetbrains.handson.kmm.shared.network.PropertiesApi
import me.tatarka.inject.annotations.Inject

@Inject
class PropertiesRepository(
    private val api: PropertiesApi
) {

    internal suspend fun getAllProperties(): List<Property> {
        return api.getAllProperties().data.propertySearch.properties
    }
}
