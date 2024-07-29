package ru.alexandrorlov.incetrotest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.alexandrorlov.incetrotest.feature.ui.screen.DetailScreen
import ru.alexandrorlov.incetrotest.feature.ui.screen.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = Screen.Main.route(),
            arguments = Screen.Detail.arguments(),
        ) { navBackStackEntry ->
            navBackStackEntry.destination
            MainScreen(
                navigateDetailScreen = { id ->
                    navController.navigate(
                        Screen.Detail.createRouteWithArgs(id)
                    )
                },
            )
        }

        composable(
            route = Screen.Detail.route(),
        ) {
            DetailScreen(
                navController = navController,
            )
        }
    }
}
