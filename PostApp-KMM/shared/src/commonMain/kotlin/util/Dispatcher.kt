// Package declaration for utility classes.
package util

// Import CoroutineDispatcher for managing coroutine contexts.
import kotlinx.coroutines.CoroutineDispatcher

// Interface to abstract coroutine dispatchers.
interface Dispatcher {
    val ioDispatcher: CoroutineDispatcher // Provides the IO dispatcher for executing IO tasks.
}

// Declaration of an expected function to supply a Dispatcher implementation per platform.
expect fun provideDispatcher(): Dispatcher