package com.riteshkumar.ezetap.data.dto

import com.google.gson.annotations.SerializedName
import com.riteshkumar.ezetap.domain.model.UIData

data class UIDataDto(
    val hint: String?,
    val key: String?,
    @SerializedName("uitype")
    val uiType: String?,
    val value: String?
)

fun UIDataDto.toUiData() = UIData(
        hint = hint ?: "",
        key = key ?: "",
        uiType = uiType ?: "",
        value = value ?: ""
    )