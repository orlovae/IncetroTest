package ru.alexandrorlov.incetrotest.detail.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository,
    private val favoriteRepository: FavoriteRepository,
) {

    suspend fun getOrganizationById(id: Long): Flow<OrganizationDBO> =
        detailRepository.getOrganizationById(id)

    suspend fun changeFavorite(id: Long) =
        favoriteRepository.changeFavorite(id)
}