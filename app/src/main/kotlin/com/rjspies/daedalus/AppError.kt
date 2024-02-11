package com.rjspies.daedalus

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class AppError : Parcelable

sealed class AddWeightError : AppError() {
    data object ParseFloatError : AddWeightError()
}
