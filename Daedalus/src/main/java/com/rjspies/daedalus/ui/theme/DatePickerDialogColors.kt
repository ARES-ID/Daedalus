package com.rjspies.daedalus.ui.theme

import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun daedalusDatePickerDialogColors() = DatePickerDefaults.colors(
    containerColor = DaedalusTheme.colors.background,
    titleContentColor = DaedalusTheme.colors.text,
    headlineContentColor = DaedalusTheme.colors.text,
    weekdayContentColor = DaedalusTheme.colors.text,
    subheadContentColor = DaedalusTheme.colors.primary,
    yearContentColor = DaedalusTheme.colors.text,
    currentYearContentColor = DaedalusTheme.colors.text,
    selectedYearContentColor = DaedalusTheme.colors.onPrimary,
    selectedYearContainerColor = DaedalusTheme.colors.primary,
    dayContentColor = DaedalusTheme.colors.text,
    disabledDayContentColor = DaedalusTheme.colors.disabled,
    selectedDayContentColor = DaedalusTheme.colors.onPrimary,
    disabledSelectedDayContentColor = DaedalusTheme.colors.disabled,
    selectedDayContainerColor = DaedalusTheme.colors.primary,
    disabledSelectedDayContainerColor = DaedalusTheme.colors.primary.copy(alpha = .3f),
    todayContentColor = DaedalusTheme.colors.text,
    todayDateBorderColor = DaedalusTheme.colors.primary,
    dayInSelectionRangeContentColor = DaedalusTheme.colors.onPrimary,
    dayInSelectionRangeContainerColor = DaedalusTheme.colors.primary,
)
