package dev.donmanuel.roomexample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform