package com.riteshkumar.ezetap.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIData(
    val hint: String = "",
    val key: String = "",
    val uiType: String = "",
    var value: String = ""
) : Parcelable