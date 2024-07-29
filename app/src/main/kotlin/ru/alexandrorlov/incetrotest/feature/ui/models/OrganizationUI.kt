package ru.alexandrorlov.incetrotest.feature.ui.models

import androidx.annotation.DrawableRes
import ru.alexandrorlov.incetrotest.utils.StringValue

data class OrganizationUI(
    val averageCheck: StringValue,
    val cuisines: StringValue,
    val id: Long,
    @DrawableRes
    val idIcon: Int,
    val name: StringValue,
    val photo: String,
    val isFavorite: Boolean,
    val rate: String,
)
