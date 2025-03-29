package dev.donmanuel.restexample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform