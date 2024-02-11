package com.rjspies.daedalus.ui.weighthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@KoinViewModel
internal class WeightHistoryViewModel(private val service: WeightService) : ViewModel() {
    val weights = service.weightsDescending().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    suspend fun deleteWeight(weight: Weight) = service.deleteWeight(weight)
}
