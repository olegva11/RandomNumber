package com.example.randomnumber.route

sealed class ScreenRoute(val route: String) {
    object StartScreen : ScreenRoute("MainActivity")
    object DetailScreen : ScreenRoute("DetailScreen")
}