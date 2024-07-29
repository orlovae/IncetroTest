package ru.alexandrorlov.incetrotest.feature.ui.models

import androidx.annotation.DrawableRes
import ru.alexandrorlov.incetrotest.utils.StringValue


data class OrganizationDetailUI(
    val id: Long,
    val name: StringValue,
    @DrawableRes
    val idIcon: Int,
    val categoryName: StringValue,
    val photos: List<String>,
    val rate: String,
    val averageCheck: StringValue,
    val isFavorite: Boolean,
    val detailedInfo: StringValue,
)