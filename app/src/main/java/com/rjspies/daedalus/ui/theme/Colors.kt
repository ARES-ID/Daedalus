package com.rjspies.daedalus.ui.theme

import androidx.compose.ui.graphics.Color

interface DaedalusColors {
    val primary: Color
    val onPrimary: Color
    val secondary: Color
    val onSecondary: Color
    val text: Color
    val background: Color
    val positive: Color
    val negative: Color
    val outlinedTextFieldUnfocused: Color
    val disabled: Color
}

object DaedalusLightColors : DaedalusColors {
    override val primary: Color
        get() = Color(0xFF006C4D)
    override val onPrimary: Color
        get() = Color(0xFFFFFFFF)
    override val secondary: Color
        get() = Color(0xFF4C6358)
    override val onSecondary: Color
        get() = Color(0xFFFFFFFF)
    override val text: Color
        get() = Color(0xFF191C1A)
    override val background: Color
        get() = Color(0xFFFBFDF9)
    override val positive: Color
        get() = Color(0xFF009900)
    override val negative: Color
        get() = Color(0xFFF70202)
    override val outlinedTextFieldUnfocused: Color
        get() = text.copy(alpha = .6f)
    override val disabled: Color
        get() = text.copy(alpha = .3f)
}

object DaedalusDarkColors : DaedalusColors {
    override val primary: Color
        get() = Color(0xFF6BDBAD)
    override val onPrimary: Color
        get() = Color(0xFF003826)
    override val secondary: Color
        get() = Color(0xFFB3CCBE)
    override val onSecondary: Color
        get() = Color(0xFF1F352B)
    override val text: Color
        get() = Color(0xFFE1E3DF)
    override val background: Color
        get() = Color(0xFF191C1A)
    override val positive: Color
        get() = Color(0xFF00A800)
    override val negative: Color
        get() = Color(0xFFFD2C4E)
    override val outlinedTextFieldUnfocused: Color
        get() = text.copy(alpha = .6f)
    override val disabled: Color
        get() = text.copy(alpha = .3f)
}
