package com.kentrell.rethink

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kentrell.ui_blog.BlogGraph
import com.kentrell.ui_blog.blogGraph

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BlogGraph.route,
    ) {
        blogGraph()
    }
}