package br.ind.siam.alcinolago.maleta.di

import androidx.room.Room
import br.ind.siam.alcinolago.maleta.Constants
import br.ind.siam.alcinolago.maleta.core.preference.PreferenceRepository
import br.ind.siam.alcinolago.maleta.core.preference.PreferenceRepositoryImpl
import br.ind.siam.alcinolago.maleta.data.database.AppDatabase
import br.ind.siam.alcinolago.maleta.domain.interactor.login.LoginInteractor
import br.ind.siam.alcinolago.maleta.domain.interactor.login.LoginInteractorImpl
import br.ind.siam.alcinolago.maleta.presentation.booking.list.ListBookingViewModel
import br.ind.siam.alcinolago.maleta.presentation.login.LoginViewModel
import br.ind.siam.alcinolago.maleta.provider.string.StringProvider
import br.ind.siam.alcinolago.maleta.provider.string.StringProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appInject = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            Constants.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().loginDao() }
    single { get<AppDatabase>().bookingDao() }

    single<LoginInteractor> { LoginInteractorImpl(get()) }
    single<PreferenceRepository> { PreferenceRepositoryImpl(androidContext()) }

    single<StringProvider> { StringProviderImpl(androidContext()) }

    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { ListBookingViewModel(get()) }
}