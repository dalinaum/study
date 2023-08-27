package io.github.dalinuam.kotlinmultiplatformsandbox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform