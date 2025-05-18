package dev.donmanuel.cursocompose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform