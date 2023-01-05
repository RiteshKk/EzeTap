package com.riteshkumar.ezetap.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EzeTapModel(
    val headingText: String = "",
    val logoUrl: String = "",
    val uiData: List<UIData> = emptyList()
) : Parcelable