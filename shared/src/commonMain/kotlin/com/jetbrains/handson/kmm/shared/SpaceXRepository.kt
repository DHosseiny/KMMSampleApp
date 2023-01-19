package com.jetbrains.handson.kmm.shared

import com.jetbrains.handson.kmm.shared.cache.LaunchesLocalDataSource
import com.jetbrains.handson.kmm.shared.entity.RocketLaunchDto
import com.jetbrains.handson.kmm.shared.network.LaunchesApi
import me.tatarka.inject.annotations.Inject

@Inject
class SpaceXRepository(
    private val launchesLocalDataSource: LaunchesLocalDataSource,
    private val api: LaunchesApi
) {

    internal suspend fun getLaunches(forceReload: Boolean): List<RocketLaunchDto> {
        val cachedLaunches = launchesLocalDataSource.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                launchesLocalDataSource.clearDatabase()
                launchesLocalDataSource.createLaunches(it)
            }
        }
    }
}
