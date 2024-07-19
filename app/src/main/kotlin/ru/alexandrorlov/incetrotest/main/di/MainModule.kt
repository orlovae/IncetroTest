package ru.alexandrorlov.incetrotest.main.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create
import ru.alexandrorlov.incetrotest.data.local.AppDatabase
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.source.api.MainApi
import ru.alexandrorlov.incetrotest.di.ViewModelKey
import ru.alexandrorlov.incetrotest.main.domain.repository.MainRepository
import ru.alexandrorlov.incetrotest.ui.viewmodel.MainViewModel

@Module(includes = [MainBindModule::class])
class MainModule {
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create()

    @Provides
    fun provideDatabaseMessages(db: AppDatabase): OrganizationsDao {
        return db.organizationDao()
    }


    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideMainViewModel(mainRepository: MainRepository): ViewModel =
        MainViewModel(mainRepository)
}