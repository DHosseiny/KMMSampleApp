package com.jetbrains.handson.kmm.shared.uimain

import com.jetbrains.handson.kmm.shared.PropertiesRepository
import com.jetbrains.handson.kmm.shared.entity.Properties
import com.jetbrains.handson.kmm.shared.entity.Property

suspend fun PropertiesRepository.getAllProperties(): List<Property> {
    return getAllProperties().map {
            it // TODO
        }
}