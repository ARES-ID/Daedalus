package com.rjspies.daedalus.ui.weighthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.domain.GetWeightsDescendingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class WeightHistoryViewModel(
    private val service: WeightService,
    getWeightsDescendingUseCase: GetWeightsDescendingUseCase
) : ViewModel() {
    val weights = getWeightsDescendingUseCase.perform().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    suspend fun deleteWeight(weight: Weight) = service.deleteWeight(weight)
}
