package ru.alexandrorlov.incetrotest.main.ui.viewmodel

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
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.main.domain.usecase.MainUseCase
import ru.alexandrorlov.incetrotest.main.ui.mapper.toUI
import ru.alexandrorlov.incetrotest.main.ui.models.MainState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state: StateFlow<MainState> = _state.asStateFlow()

    val onClickFavoriteIcon = MutableSharedFlow<Long>(extraBufferCapacity = 1)

    val countFavoriteIcon = MutableStateFlow(0)

    init {
        loadAllOrganization()
        observeOnClick()
        observeCountFavorite()
    }

    private fun loadAllOrganization() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                mainUseCase.getAllOrganizations()
                    .map {list ->
                        list.map { it.toUI() }
                    }.collect {
                        _state.emit(MainState.Content(it))
                    }
            }.getOrElse {
                _state.emit(MainState.Error(it.message ?: "ERROR"))
            }
        }
    }

    private fun observeOnClick() {
        onClickFavoriteIcon
            .onEach {
                mainUseCase.changeFavorite(it)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun observeCountFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            mainUseCase.getAllFavorite().collect {
                countFavoriteIcon.emit(getCountFavorite(it))
            }
        }
    }

    private fun getCountFavorite(list: List<OrganizationDBO>): Int =
        list.count {
            it.isFavorite
        }
}