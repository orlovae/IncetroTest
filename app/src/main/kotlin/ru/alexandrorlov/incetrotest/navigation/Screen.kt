package ru.alexandrorlov.incetrotest.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.alexandrorlov.incetrotest.utils.Constant.ID_ARG_NAME

sealed class Screen {

    protected abstract val route: String
    abstract fun route(): String

    data object Main : Screen() {

        override val route: String = "main"

        override fun route(): String = route
    }

    data object Detail : Screen() {

        override val route: String = "detail"
        private const val idItem = ID_ARG_NAME

        override fun route(): String = "$route/{$idItem}"

        fun createRouteWithArgs(id: Long): String = "$route/$id"

        fun arguments(): List<NamedNavArgument> {
            return listOf(
                navArgument(idItem) { type = NavType.StringType }
            )
        }
    }

    companion object Constant {
        const val idItem = ID_ARG_NAME
    }
}