package com.rjspies.daedalus.weight.addweight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rjspies.daedalus.weight.service.WeightService
import com.rjspies.daedalus.weight.service.data.Weight
import kotlinx.coroutines.flow.StateFlow
import org.koin.android.annotation.KoinViewModel
import java.time.Instant

private const val HANDLE_KEY_WEIGHT_ERROR = "weight_error"
private const val HANDLE_KEY_WEIGHT = "weight"
private const val HANDLE_KEY_NOTE = "note"
private const val HANDLE_KEY_SHOULD_SHOW_BANNER = "should_show_banner"
private const val HANDLE_KEY_SELECTED_DATE = "selected_date"

@KoinViewModel
class AddWeightViewModel(private val savedStateHandle: SavedStateHandle, private val service: WeightService) : ViewModel() {
    val weightError: StateFlow<WeightError> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_WEIGHT_ERROR,
        initialValue = WeightError.None,
    )

    val weight: StateFlow<String?> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_WEIGHT,
        initialValue = null,
    )

    val note: StateFlow<String?> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_NOTE,
        initialValue = null,
    )

    val shouldShowBanner: StateFlow<Boolean> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_SHOULD_SHOW_BANNER,
        initialValue = false,
    )

    val selectedDate: StateFlow<Instant> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_SELECTED_DATE,
        initialValue = Instant.now(),
    )

    fun resetWeightError() {
        savedStateHandle[HANDLE_KEY_WEIGHT_ERROR] = WeightError.None
    }

    fun setWeightError(value: WeightError) {
        savedStateHandle[HANDLE_KEY_WEIGHT_ERROR] = value
    }

    fun setWeight(value: String) {
        savedStateHandle[HANDLE_KEY_WEIGHT] = value
    }

    fun setNote(value: String) {
        savedStateHandle[HANDLE_KEY_NOTE] = value
    }

    fun setShouldShowBanner(value: Boolean) {
        savedStateHandle[HANDLE_KEY_SHOULD_SHOW_BANNER] = value
    }

    fun setSelectedDate(value: Instant) {
        savedStateHandle[HANDLE_KEY_SELECTED_DATE] = value
    }

    suspend fun saveWeight(weight: Weight) = service.saveWeight(weight)
}
