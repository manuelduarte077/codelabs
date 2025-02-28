package util

import org.koin.core.context.startKoin
import di.getSharedModules

fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }.koin
}