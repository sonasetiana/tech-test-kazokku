package com.sonasetiana.techtestkozokku.utils

fun String?.upFirst(): String {
    return if (this != null) {
        this.substring(0, 1).uppercase() + this.substring(1, this.length)
    } else {
        ""
    }
}