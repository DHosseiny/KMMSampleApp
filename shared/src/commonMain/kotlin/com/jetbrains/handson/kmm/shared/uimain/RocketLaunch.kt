package com.jetbrains.handson.kmm.shared.uimain

import com.jetbrains.handson.kmm.shared.entity.Links
import com.jetbrains.handson.kmm.shared.entity.RocketLaunchDto
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class RocketLaunch(
    val flightNumber: Int,
    val missionName: String,
    launchDateUTC: String,
    val details: String?,
    val launchSuccess: Boolean?,
    val links: Links
) {
    var launchYear = launchDateUTC.toInstant().toLocalDateTime(TimeZone.UTC).year

    companion object {
        fun RocketLaunchDto.toRocketLaunch(): RocketLaunch {
            return RocketLaunch(
                flightNumber, missionName, launchDateUTC, details, launchSuccess, links
            )
        }
    }
}
