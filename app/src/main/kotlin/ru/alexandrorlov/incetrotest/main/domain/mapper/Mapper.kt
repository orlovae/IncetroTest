package ru.alexandrorlov.incetrotest.main.domain.mapper

import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import kotlin.random.Random

fun OrganizationRemote.toDB(): OrganizationsDBO =
    OrganizationsDBO(
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

