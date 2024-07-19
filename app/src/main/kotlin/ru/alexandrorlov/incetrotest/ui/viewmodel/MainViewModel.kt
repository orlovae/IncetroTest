package ru.alexandrorlov.incetrotest.ui.viewmodel

//import ru.alexandrorlov.incetrotest.common.BaseState
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
import ru.alexandrorlov.incetrotest.main.domain.repository.MainRepository
import ru.alexandrorlov.incetrotest.ui.mapper.fromDBOToUI
import ru.alexandrorlov.incetrotest.ui.models.MainState
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
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
        viewModelScope.launch {
            kotlin.runCatching {
                mainRepository.getAllOrganizations()
                    .map {list ->
                        Timber.tag("OAE").d("list = $list")
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
                mainRepository.changeFavorite(it)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun observeCountFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getAllFavorite().collect {
                countFavoriteIcon.emit(getCountFavorite(it))
            }
        }
    }

    private fun getCountFavorite(list: List<OrganizationsDBO>): Int =
        list.count {
            it.isFavorite
        }
}