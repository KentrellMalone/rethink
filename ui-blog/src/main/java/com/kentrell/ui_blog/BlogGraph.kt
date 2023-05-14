package com.kentrell.ui_blog

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kentrell.ui_blog.blog.BlogScreen

object BlogGraph {
    const val route = "blog-graph"
    const val startDestination = Blog.route

    object Blog {
        const val route = "blog"
    }
}

fun NavGraphBuilder.blogGraph() {
    navigation(
        route = BlogGraph.route,
        startDestination = BlogGraph.startDestination,
    ) {
        composable(BlogGraph.Blog.route) {
            BlogScreen()
        }
    }
}