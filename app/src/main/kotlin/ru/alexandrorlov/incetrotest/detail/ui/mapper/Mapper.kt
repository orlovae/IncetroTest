package ru.alexandrorlov.incetrotest.detail.ui.mapper

import ru.alexandrorlov.incetrotest.R
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.detail.ui.models.OrganizationDetailUI
import ru.alexandrorlov.incetrotest.utils.getAverageCheck
import ru.alexandrorlov.incetrotest.utils.getIconId
import ru.alexandrorlov.incetrotest.utils.getStringValueFromString

fun OrganizationDBO.toUI(): OrganizationDetailUI =
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
