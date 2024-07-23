package ru.alexandrorlov.incetrotest.detail.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.alexandrorlov.incetrotest.detail.domain.usecase.DetailUseCase
import ru.alexandrorlov.incetrotest.detail.ui.viewmodel.DetailViewModel
import ru.alexandrorlov.incetrotest.di.ViewModelKey

@Module(includes = [DetailBindModule::class])
class DetailModule {

    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @Provides
    fun provideDetailViewModel(detailUseCase: DetailUseCase): ViewModel =
        DetailViewModel(detailUseCase)
}