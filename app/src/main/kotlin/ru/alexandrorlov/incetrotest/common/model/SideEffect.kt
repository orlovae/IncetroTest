package ru.alexandrorlov.incetrotest.common.model

sealed class SideEffect {
    data class SnackBar(val message: String) : SideEffect()
}