package dev.donmanuel.navexamplekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform