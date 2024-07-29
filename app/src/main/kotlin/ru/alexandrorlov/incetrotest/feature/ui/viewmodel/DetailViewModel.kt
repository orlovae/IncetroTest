package ru.alexandrorlov.incetrotest.feature.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import ru.alexandrorlov.incetrotest.common.di.ViewModelAssistedFactory
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.common.model.SideEffect
import ru.alexandrorlov.incetrotest.feature.domain.usecase.DetailUseCase
import ru.alexandrorlov.incetrotest.feature.ui.mapper.toOrganizationDetailUI
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationDetailUI
import ru.alexandrorlov.incetrotest.utils.Constant.ID_ARG_NAME
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
    @Assisted private val handle: SavedStateHandle,
    private val detailUseCase: DetailUseCase,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<DetailViewModel>

    private val _state: MutableStateFlow<ScreenState<OrganizationDetailUI>> =
        MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState<OrganizationDetailUI>> = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val sideEffect: SharedFlow<SideEffect> = _sideEffect

    val onClickFavoriteIcon: MutableSharedFlow<Long> =
        MutableSharedFlow(extraBufferCapacity = 1)

    val id: Long = handle.get<String>(ID_ARG_NAME)?.toLong() ?: throw IllegalArgumentException("Invalid item id")

    init {
        loadOrganization()
        observeOnClick()
    }

    private fun loadOrganization() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                detailUseCase.getOrganizationById(id)
                    .onEach {
                        Timber.tag("OAE").d("item =$it")
                    }
                    .map {
                        it.toOrganizationDetailUI()
                    }.collect {
                            _state.emit(ScreenState.Content(it))
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