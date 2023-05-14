package com.kentrell.ui_blog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

object BlogGraph {
    const val route = "blog-graph"
    const val startDestination = Main.route

    object Main {
        const val route = "main"
    }
}

fun NavGraphBuilder.blogGraph() {
    navigation(
        route = BlogGraph.route,
        startDestination = BlogGraph.startDestination,
    ) {
        composable(BlogGraph.Main.route) {

        }
    }
}