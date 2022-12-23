package com.sonasetiana.techtestkozokku.presentation.navigation

sealed class Screen(val route: String) {
    object User : Screen("user")
    object Post : Screen("post")
    object Favorite : Screen("favorite")
    object Detail: Screen("detail/{id}") {
        fun createRoute(id: String) = "detail/$id"
    }
}

