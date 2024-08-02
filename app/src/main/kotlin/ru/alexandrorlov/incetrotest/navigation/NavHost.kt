package ru.alexandrorlov.incetrotest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            arguments = listOf(navArgument(Screen.idItem) { type = NavType.LongType })
        ) {

            val arguments = requireNotNull(it.arguments)
            val id = arguments.getLong(Screen.idItem, -1L)
            DetailScreen(
                id = id,
                navController = navController,
            )
        }
    }
}
