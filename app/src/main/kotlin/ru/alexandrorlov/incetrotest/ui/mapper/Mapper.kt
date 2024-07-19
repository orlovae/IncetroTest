package ru.alexandrorlov.incetrotest.ui.mapper

import androidx.annotation.StringRes
import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import ru.alexandrorlov.incetrotest.ui.models.OrganizationUi
import ru.alexandrorlov.incetrotest.utils.StringValue

fun OrganizationRemote.toUI(): OrganizationUi =
    OrganizationUi(
        averageCheck = getAverageCheck(this.averageCheck),
        cuisines = getCuisines(this.cuisines),
        id = this.id ?: -1L,
        idIcon = getIconId(this.isFavorite),
        name = getStringValueFromString(this.name, R.string.no_check),
        photo = this.photo,
        rate = this.rate,
    )

private fun getAverageCheck(list: List<Double>): StringValue =
    if (list.isEmpty()) {
        StringValue.StringResource(R.string.no_check)
    } else {
        val average: Double = list.average()
        val outString: String = average.toInt().toString()

        StringValue.DynamicString(outString)
    }

private fun getCuisines(list: List<String>): StringValue =
    if (list.isEmpty()) {
        StringValue.StringResource(R.string.no_cuisines)
    } else {
        StringValue.DynamicString(list.joinToString())
    }

private fun getIconId(isFavorite: Boolean): Int =
    if (isFavorite) {
        R.drawable.baseline_favorite_24
    } else {
        R.drawable.baseline_favorite_border_24
    }

private fun getStringValueFromString(value: String, @StringRes stringId: Int): StringValue =
    if (value.isBlank()) {
        StringValue.StringResource(stringId)
    } else {
        StringValue.DynamicString(value)
    }

fun OrganizationsDBO.fromDBOToUI(): OrganizationUi =
    OrganizationUi(
        averageCheck = getAverageCheck(this.averageCheck),
        cuisines = getCuisines(this.cuisines),
        id = this.id,
        idIcon = getIconId(this.isFavorite),
        name = getStringValueFromString(this.name, R.string.no_check),
        photo = this.photo,
        rate = this.rate,
    )

fun OrganizationsDBO.fromDBOToUI(isFavorite: Boolean): OrganizationUi =
    OrganizationUi(
        averageCheck = getAverageCheck(this.averageCheck),
        cuisines = getCuisines(this.cuisines),
        id = this.id,
        idIcon = getIconId(isFavorite),
        name = getStringValueFromString(this.name, R.string.no_check),
        photo = this.photo,
        rate = this.rate,
    )
