package io.github.dalinuam.ktorandsqldelight

import io.github.dalinuam.ktorandsqldelight.cache.Database
import io.github.dalinuam.ktorandsqldelight.cache.DatabaseDriverFactory
import io.github.dalinuam.ktorandsqldelight.entity.RocketLaunch
import network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cacheLaunches = database.getAllLaunches()
        return if (cacheLaunches.isNotEmpty() && !forceReload) {
            cacheLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}