package dev.donmanuel.postapi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform