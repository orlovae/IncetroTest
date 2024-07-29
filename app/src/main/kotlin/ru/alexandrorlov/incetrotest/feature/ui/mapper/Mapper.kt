package ru.alexandrorlov.incetrotest.feature.ui.mapper

import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationDetailUI
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationUI
import ru.alexandrorlov.incetrotest.utils.getAverageCheck
import ru.alexandrorlov.incetrotest.utils.getCuisines
import ru.alexandrorlov.incetrotest.utils.getIconId
import ru.alexandrorlov.incetrotest.utils.getStringValueFromString

fun OrganizationDBO.toOrganizationDetailUI(): OrganizationDetailUI =
    OrganizationDetailUI(
        id = this.id,
        name = getStringValueFromString(this.name, R.string.no_check),
        idIcon = getIconId(this.isFavorite),
        categoryName = getStringValueFromString(this.name, R.string.no_category),
        photos = this.detailPhotoList,
        rate = this.rate,
        averageCheck = getAverageCheck(this.averageCheck),
        isFavorite = this.isFavorite,
        detailedInfo = getStringValueFromString(this.description, R.string.no_description),
    )

fun OrganizationDBO.toOrganizationUI(): OrganizationUI =
    OrganizationUI(
        averageCheck = getAverageCheck(this.averageCheck),
        cuisines = getCuisines(this.cuisines),
        id = this.id,
        idIcon = getIconId(this.isFavorite),
        name = getStringValueFromString(this.name, R.string.no_check),
        photo = this.photo,
        isFavorite = this.isFavorite,
        rate = this.rate,
    )
