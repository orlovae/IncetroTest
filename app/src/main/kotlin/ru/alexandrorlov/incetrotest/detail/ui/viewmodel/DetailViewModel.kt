package ru.alexandrorlov.incetrotest.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import ru.alexandrorlov.incetrotest.common.model.ScreenState
import ru.alexandrorlov.incetrotest.detail.domain.usecase.DetailUseCase
import ru.alexandrorlov.incetrotest.detail.ui.mapper.toUI
import ru.alexandrorlov.incetrotest.detail.ui.models.OrganizationDetailUI
import timber.log.Timber
import javax.inject.Inject

class DetailViewModel @Inject constructor(
//    private val id: Long,
    private val detailUseCase: DetailUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<OrganizationDetailUI>> = MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState<OrganizationDetailUI>> = _state.asStateFlow()

    val onClickFavoriteIcon = MutableSharedFlow<Long>(extraBufferCapacity = 1)

    init {
        loadOrganization()
        observeOnClick()
    }

    private fun loadOrganization() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                detailUseCase.getOrganizationById(2367)
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
                Timber.tag("OAE").d("DetailViewModel start observeOnClick")
                detailUseCase.changeFavorite(it)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}