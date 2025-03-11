package dev.donmanuel.dragonball

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform