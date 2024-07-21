package ru.alexandrorlov.incetrotest.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.detail.domain.repository.DetailRepository
import ru.alexandrorlov.incetrotest.ui.models.MainState
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val state: StateFlow<MainState> = _state.asStateFlow()

    val onClickFavoriteIcon = MutableSharedFlow<Long>(extraBufferCapacity = 1)

    val countFavoriteIcon = MutableStateFlow(0)

//    init {
//        loadAllOrganization()
//        observeOnClick()
//        observeCountFavorite()
//    }
//
//    private fun loadAllOrganization() {
//        viewModelScope.launch {
//            kotlin.runCatching {
//                detailRepository.getAllOrganizations()
//                    .map {list ->
//                        Timber.tag("OAE").d("list = $list")
//                        list.map { it.fromDBOToUI() }
//                    }.collect {
//                            _state.emit(MainState.Content(it))
//                    }
//            }.getOrElse {
//                _state.emit(MainState.Error(it.message ?: "ERROR"))
//            }
//        }
//    }
//
//    private fun observeOnClick() {
//        onClickFavoriteIcon
//            .onEach {
//                detailRepository.changeFavorite(it)
//            }
//            .flowOn(Dispatchers.IO)
//            .launchIn(viewModelScope)
//    }
//
//    private fun observeCountFavorite() {
//        viewModelScope.launch(Dispatchers.IO) {
//            detailRepository.getAllFavorite().collect {
//                countFavoriteIcon.emit(getCountFavorite(it))
//            }
//        }
//    }

    private fun getCountFavorite(list: List<OrganizationsDBO>): Int =
        list.count {
            it.isFavorite
        }
}