package ru.alexandrorlov.incetrotest.feature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.common.model.SideEffect
import ru.alexandrorlov.incetrotest.feature.domain.usecase.DetailUseCase
import ru.alexandrorlov.incetrotest.feature.ui.mapper.toOrganizationDetailUI
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationDetailUI
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<OrganizationDetailUI>> =
        MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState<OrganizationDetailUI>> = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val sideEffect: SharedFlow<SideEffect> = _sideEffect

    val onClickFavoriteIcon: MutableSharedFlow<Long> =
        MutableSharedFlow(extraBufferCapacity = 1)

    val idState: MutableStateFlow<Long> = MutableStateFlow(-1L)

    init {
        loadOrganization()
        observeOnClick()
    }

    private fun loadOrganization() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                idState.combine(detailUseCase.getOrganization()) { id, list ->

                    if (id == -1L) {

                        ScreenState.Loading

                    } else {

                        val organization: OrganizationDetailUI = list.first {
                            it.id == id
                        }.toOrganizationDetailUI()

                        ScreenState.Content(organization)
                    }
                }.collect {
                    _state.emit(it)
                }
            }.getOrElse {
                _state.emit(ScreenState.Error(it.message ?: "ERROR"))
            }
        }
    }

    private fun observeOnClick() {
        onClickFavoriteIcon
            .onEach {
                detailUseCase.changeFavorite(it)
            }
            .retry()
            .catch {
                _sideEffect.emit(SideEffect.SnackBar(it.message ?: "ERROR"))
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}