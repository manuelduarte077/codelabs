package dev.donmanuel.postapi.core.network

import dev.donmanuel.postapi.core.Error

enum class DataError : Error {
    UNKNOWN, DATA_NOT_FOUND
}