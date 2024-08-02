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
import ru.alexandrorlov.incetrotest.feature.domain.usecase.MainUseCase
import ru.alexandrorlov.incetrotest.feature.ui.mapper.toOrganizationUI
import ru.alexandrorlov.incetrotest.feature.ui.models.OrganizationUI
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<ScreenState<List<OrganizationUI>>> =
        MutableStateFlow(ScreenState.Loading)
    val state: StateFlow<ScreenState<List<OrganizationUI>>> = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val sideEffect: SharedFlow<SideEffect> = _sideEffect

    private val _listOrganization = MutableStateFlow(emptyList<OrganizationUI>())

    val onClickIconFavoriteItem = MutableSharedFlow<Long>(extraBufferCapacity = 1)

    val onClickIconFavoriteTopbar = MutableStateFlow(false)

    val countFavoriteIcon = MutableStateFlow(0)

    init {
        getAllOrganizations()
        observeListOrganization()
        observeOnClickIconFavoriteItem()
        observeCountFavorite()
    }

    private fun getAllOrganizations() {
        viewModelScope.launch {
            kotlin.runCatching {
                mainUseCase.getAllOrganizations()
                    .combine(onClickIconFavoriteTopbar) { list, isFavorite ->

                        val outList = if (isFavorite) {
                            list.filter { it.isFavorite }
                        } else {
                            list
                        }

                        outList.map { it.toOrganizationUI() }
                    }.collect {
                        _listOrganization.emit(it)
                    }
            }.getOrElse {
                _state.emit(ScreenState.Error("Error ${it.message}"))
            }
        }

    }

    private fun observeListOrganization() {
        _listOrganization
            .onEach {
                _state.emit(ScreenState.Content(it))
            }
            .launchIn(viewModelScope)
    }

    private fun observeOnClickIconFavoriteItem() {
        onClickIconFavoriteItem
            .onEach {
                mainUseCase.changeFavorite(it)
            }
            .retry()
            .catch {
                _sideEffect.emit(SideEffect.SnackBar(it.message ?: "ERROR"))
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun observeCountFavorite() {
        _listOrganization
            .onEach { list ->
                list.filter { it.isFavorite }

                val count = getCountFavorite(list)

                countFavoriteIcon.emit(count)
            }
            .launchIn(viewModelScope)
    }

    private fun getCountFavorite(list: List<OrganizationUI>): Int =
        list.count {
            it.isFavorite
        }
}
