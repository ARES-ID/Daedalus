package com.rjspies.daedalus.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

public interface AppError : Parcelable

@Parcelize
public sealed class AddWeightError : AppError {
    public data object ParseFloatError : AddWeightError()
}
