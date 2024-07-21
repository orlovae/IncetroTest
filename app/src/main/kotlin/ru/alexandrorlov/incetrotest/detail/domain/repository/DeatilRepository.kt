package ru.alexandrorlov.incetrotest.detail.domain.repository

import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO

interface DetailRepository {

    suspend fun getOrganizationById(id: Long): OrganizationsDBO
}