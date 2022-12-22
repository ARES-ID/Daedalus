package me.renespies.daedalus.ui.theme

import androidx.compose.ui.graphics.Color

interface DaedalusColors {
    val text: Color
    val background: Color
}

object DaedalusLightColors : DaedalusColors {
    override val text: Color
        get() = Color(0xFF000000)
    override val background: Color
        get() = Color(0xFFFFFFFF)
}

object DaedalusDarkColors : DaedalusColors {
    override val text: Color
        get() = Color(0xFFFFFFFF)
    override val background: Color
        get() = Color(0xFF000000)
}
