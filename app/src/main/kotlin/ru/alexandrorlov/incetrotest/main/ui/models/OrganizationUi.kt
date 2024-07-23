package ru.alexandrorlov.incetrotest.main.ui.models

import androidx.annotation.DrawableRes
import ru.alexandrorlov.incetrotest.utils.StringValue

data class OrganizationUi(
    val averageCheck: StringValue,
    val cuisines: StringValue,
    val id: Long,
    @DrawableRes
    val idIcon: Int,
    val name: StringValue,
    val photo: String,
    val rate: String,
)
