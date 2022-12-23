package com.sonasetiana.techtestkozokku.data.local.db

sealed class RoomResult<out T: Any?> {
    data class Success<T>(val data: T): RoomResult<T>()
    data class Failure(val message: String): RoomResult<Nothing>()
}
