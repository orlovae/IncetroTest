package ru.alexandrorlov.incetrotest.utils

import androidx.annotation.StringRes
import ru.alexandrorlov.incetrotest.R

fun getAverageCheck(list: List<Double>): StringValue =
    if (list.isEmpty()) {
        StringValue.StringResource(R.string.no_check)
    } else {
        val average: Double = list.average()
        val outString: String = average.toInt().toString()

        StringValue.DynamicString(outString)
    }

fun getCuisines(list: List<String>): StringValue =
    if (list.isEmpty()) {
        StringValue.StringResource(R.string.no_cuisines)
    } else {
        StringValue.DynamicString(list.joinToString())
    }

fun getIconId(isFavorite: Boolean): Int =
    if (isFavorite) {
        R.drawable.baseline_favorite_24
    } else {
        R.drawable.baseline_favorite_border_24
    }

fun getStringValueFromString(value: String, @StringRes stringId: Int): StringValue =
    if (value.isBlank()) {
        StringValue.StringResource(stringId)
    } else {
        StringValue.DynamicString(value)
    }