package com.sonasetiana.techtestkozokku.presentation.navigation

sealed class Screen(val route: String) {
    object User : Screen("user")
    object TimeLine : Screen("timeline")
    object Favorite : Screen("favorite")
    object Detail : Screen("detail")
}

