package ru.alexandrorlov.incetrotest.detail.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexandrorlov.incetrotest.common.di.ViewModelAssistedFactory
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.detail.domain.usecase.DetailUseCase
import ru.alexandrorlov.incetrotest.detail.ui.mapper.toUI
import ru.alexandrorlov.incetrotest.detail.ui.models.OrganizationDetailUI
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
    @Assisted private val handle: SavedStateHandle,
    private val detailUseCase: DetailUseCase,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<DetailViewModel>

    private val _state: MutableStateFlow<ScreenState<OrganizationDetailUI>> = MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState<OrganizationDetailUI>> = _state.asStateFlow()

    val onClickFavoriteIcon = MutableSharedFlow<Long>(extraBufferCapacity = 1)

    val id: Long = handle.get<Long>("ID") ?: throw IllegalArgumentException("Invalid item id")

    init {
        Timber.tag("OAE").d("DetailUseCase = $detailUseCase")
        loadOrganization()
        observeOnClick()
    }

    private fun loadOrganization() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                detailUseCase.getOrganizationById(id)
                    .map {
                        it.toUI()
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
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}