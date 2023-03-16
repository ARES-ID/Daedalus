package me.renespies.daedalus.ui.theme

import androidx.compose.ui.graphics.Color

interface DaedalusColors {
    val primary: Color
    val secondary: Color
    val text: Color
    val background: Color
    val positive: Color
    val negative: Color
}

object DaedalusLightColors : DaedalusColors {
    override val primary: Color
        get() = Color(0xFF006C4D)
    override val secondary: Color
        get() = Color(0xFF4C6358)
    override val text: Color
        get() = Color(0xFF191C1A)
    override val background: Color
        get() = Color(0xFFFBFDF9)
    override val positive: Color
        get() = Color(0xFF009900)
    override val negative: Color
        get() = Color(0xFFEB0D3F)
}

object DaedalusDarkColors : DaedalusColors {
    override val primary: Color
        get() = Color(0xFF6BDBAD)
    override val text: Color
        get() = Color(0xFFE1E3DF)
    override val secondary: Color
        get() = Color(0xFFB3CCBE)
    override val background: Color
        get() = Color(0xFF191C1A)
    override val positive: Color
        get() = Color(0xFF00A800)
    override val negative: Color
        get() = Color(0xFFFD2C4E)
}
