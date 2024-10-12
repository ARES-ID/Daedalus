package com.rjspies.daedalus.koin

import androidx.room.Room
import com.rjspies.daedalus.data.WeightDatabase
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.domain.DeleteWeightUseCase
import com.rjspies.daedalus.domain.GetWeightsAscendingUseCase
import com.rjspies.daedalus.domain.GetWeightsDescendingUseCase
import com.rjspies.daedalus.ui.InsertWeightUseCase
import com.rjspies.daedalus.ui.InsertWeightViewModel
import com.rjspies.daedalus.ui.MainViewModel
import com.rjspies.daedalus.ui.SettingsViewModel
import com.rjspies.daedalus.ui.WeightGraphViewModel
import com.rjspies.daedalus.ui.WeightHistoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = WeightDatabase::class.java,
            name = "weight_database", // TODO
        ).build()
    }
    single { get<WeightDatabase>(WeightDatabase::class).weightDao() }
    single { WeightService(get()) }
    viewModel { MainViewModel(get()) }
    factory { GetWeightsAscendingUseCase(get()) }
    viewModel { WeightGraphViewModel(get()) }
    factory { GetWeightsDescendingUseCase(get()) }
    factory { DeleteWeightUseCase(get()) }
    viewModel { WeightHistoryViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    factory { InsertWeightUseCase(get()) }
    viewModel { InsertWeightViewModel(get(), get()) }
}
