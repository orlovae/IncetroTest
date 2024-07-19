package ru.alexandrorlov.incetrotest.ui.models

sealed class MainState {
    data object Loading : MainState()
    data class Content(val organizations: List<OrganizationUi>) : MainState()
    data class Error(val message: String) : MainState()
}