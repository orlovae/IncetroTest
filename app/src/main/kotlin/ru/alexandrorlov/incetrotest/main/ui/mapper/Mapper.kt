package ru.alexandrorlov.incetrotest.main.ui.mapper

import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.main.ui.models.OrganizationUi
import ru.alexandrorlov.incetrotest.utils.getAverageCheck
import ru.alexandrorlov.incetrotest.utils.getCuisines
import ru.alexandrorlov.incetrotest.utils.getIconId
import ru.alexandrorlov.incetrotest.utils.getStringValueFromString

fun OrganizationDBO.toUI(): OrganizationUi =
    OrganizationUi(
        averageCheck = getAverageCheck(this.averageCheck),
        cuisines = getCuisines(this.cuisines),
        id = this.id,
        idIcon = getIconId(this.isFavorite),
        name = getStringValueFromString(this.name, R.string.no_check),
        photo = this.photo,
        rate = this.rate,
    )
