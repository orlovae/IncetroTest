package ru.alexandrorlov.incetrotest.ui.viewmodel

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
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.main.domain.usecase.MainUseCase
import ru.alexandrorlov.incetrotest.ui.mapper.fromDBOToUI
import ru.alexandrorlov.incetrotest.ui.models.MainState
import timber.log.Timber
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
                        list.map { it.fromDBOToUI() }
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
                Timber.tag("OAE").d("size list favorite is ${it.size}")
                countFavoriteIcon.emit(getCountFavorite(it))
            }
        }
    }

    private fun getCountFavorite(list: List<OrganizationsDBO>): Int =
        list.count {
            it.isFavorite
        }
}