package com.rjspies.daedalus.ui.theme

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable

@Composable
fun daedalusOutlinedTextFieldColors(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        cursorColor = DaedalusTheme.colors.text,
        errorBorderColor = DaedalusTheme.colors.negative,
        errorCursorColor = DaedalusTheme.colors.negative,
        errorLabelColor = DaedalusTheme.colors.negative,
        errorLeadingIconColor = DaedalusTheme.colors.negative,
        errorSupportingTextColor = DaedalusTheme.colors.negative,
        errorTrailingIconColor = DaedalusTheme.colors.negative,
        unfocusedLabelColor = DaedalusTheme.colors.outlinedTextFieldUnfocused,
        unfocusedBorderColor = DaedalusTheme.colors.outlinedTextFieldUnfocused,
        unfocusedLeadingIconColor = DaedalusTheme.colors.outlinedTextFieldUnfocused,
        unfocusedSupportingTextColor = DaedalusTheme.colors.outlinedTextFieldUnfocused,
        unfocusedTrailingIconColor = DaedalusTheme.colors.outlinedTextFieldUnfocused,
        disabledBorderColor = DaedalusTheme.colors.disabled,
        disabledLabelColor = DaedalusTheme.colors.disabled,
        disabledLeadingIconColor = DaedalusTheme.colors.disabled,
        disabledPlaceholderColor = DaedalusTheme.colors.disabled,
        disabledTextColor = DaedalusTheme.colors.disabled,
        disabledSupportingTextColor = DaedalusTheme.colors.disabled,
        disabledTrailingIconColor = DaedalusTheme.colors.disabled,
        focusedLeadingIconColor = DaedalusTheme.colors.primary,
        focusedSupportingTextColor = DaedalusTheme.colors.text,
        focusedTrailingIconColor = DaedalusTheme.colors.primary,
        focusedBorderColor = DaedalusTheme.colors.primary,
        focusedLabelColor = DaedalusTheme.colors.text,
        focusedTextColor = DaedalusTheme.colors.text,
        unfocusedTextColor = DaedalusTheme.colors.text,
        errorTextColor = DaedalusTheme.colors.text,
    )
}
