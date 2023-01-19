package com.jetbrains.handson.kmm.shared.uimain

import com.jetbrains.handson.kmm.shared.SpaceXRepository
import com.jetbrains.handson.kmm.shared.uimain.RocketLaunch.Companion.toRocketLaunch

// Solution 1 UseCases
//@Inject
//class SpaceXUseCase(private val spaceXRepository: SpaceXRepository) {
//
//    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
//        return spaceXRepository.getLaunches(forceReload)
//            .map {
//                it.toRocketLaunch()
//            }
//    }
//}

// Solution 2 Extension functions
suspend fun SpaceXRepository.getLaunches(forceReload: Boolean): List<RocketLaunch> {
    return getLaunches(forceReload)
        .map {
            it.toRocketLaunch()
        }
}