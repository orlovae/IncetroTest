package ru.alexandrorlov.incetrotest.feature.domain.mapper

import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import kotlin.random.Random

fun OrganizationDetailRemote.toDB(photo: String): OrganizationDBO =
    OrganizationDBO(
        id = this.id ?: Random.nextLong(),
        averageCheck = this.averageCheck,
        cuisines = this.cuisines,
        isFavorite = this.isFavorite,
        name = this.name,
        photo = photo,
        rate = this.rate,
        description = this.detailedInfo,
        detailPhotoList = this.photos,
    )

fun OrganizationRemote.toDB(): OrganizationDBO =
    OrganizationDBO(
        id = this.id ?: Random.nextLong(),
        averageCheck = this.averageCheck,
        cuisines = this.cuisines,
        isFavorite = this.isFavorite,
        name = this.name,
        photo = this.photo,
        rate = this.rate,
        description = "",
        detailPhotoList = emptyList(),
    )
