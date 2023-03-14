package com.sindrenm.lwb.app.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

internal const val HomeGraphRoute = "/home"

fun NavGraphBuilder.homeNavGraph() {
    navigation(startDestination = "/", HomeGraphRoute) {
        homeScreen()
    }
}

private fun NavGraphBuilder.homeScreen() {
    composable("/") {
        HomeScreen()
    }
}
