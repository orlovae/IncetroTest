package ru.alexandrorlov.incetrotest.main.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.main.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val favoriteRepository: FavoriteRepository,
) {

    suspend fun changeFavorite(id: Long) =
        favoriteRepository.changeFavorite(id)

    suspend fun getAllOrganizations(): Flow<List<OrganizationsDBO>> =
        mainRepository.getAllOrganizations()

    suspend fun getAllFavorite(): Flow<List<OrganizationsDBO>> =
        mainRepository.getAllFavorite()
}