package com.rjspies.daedalus.ui.banner

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

public interface BannerPresenter {
    public suspend fun showBanner(title: String, description: String)
}

public class BannerPresenterImpl : BannerPresenter {
    private val _data: MutableStateFlow<Data> = MutableStateFlow(Data("", "", false))
    public val data: Flow<Data>
        get() = _data

    override suspend fun showBanner(title: String, description: String) {
        val data = Data(title, description, true)
        _data.value = data
        delay(1500)
        _data.value = data.copy(visible = false)
    }

    public fun hideBanner(data: Data) {
        _data.value = data.copy(visible = false)
    }
}

public data class Data(
    val title: String,
    val description: String,
    val visible: Boolean
)
