package ru.alexandrorlov.incetrotest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexandrorlov.incetrotest.App
import ru.alexandrorlov.incetrotest.common.di.Inject
import ru.alexandrorlov.incetrotest.feature.ui.screen.DetailScreen
import ru.alexandrorlov.incetrotest.feature.ui.screen.MainScreen
import ru.alexandrorlov.incetrotest.navigation.Screen
import ru.alexandrorlov.incetrotest.ui.theme.IncetroTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController: NavHostController = rememberNavController()
            IncetroTestTheme {
                Inject(
                    viewModelFactory = App.featureComponent.getViewModelFactory(),
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Main.route(),
                        ) {
                            composable(
                                route = Screen.Main.route(),
                                arguments = Screen.Detail.arguments(),
                            ) {navBackStackEntry ->
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
                                DetailScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
