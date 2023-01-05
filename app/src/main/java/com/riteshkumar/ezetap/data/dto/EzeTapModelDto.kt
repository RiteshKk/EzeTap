package com.riteshkumar.ezetap.data.dto

import com.google.gson.annotations.SerializedName
import com.riteshkumar.ezetap.domain.model.EzeTapModel

data class EzeTapModelDto(
    @SerializedName("heading-text")
    val headingText: String?,
    @SerializedName("logo-url")
    val logoUrl: String?,
    @SerializedName("uidata")
    val uiData: List<UIDataDto>?
)

fun EzeTapModelDto.toEzeTapModel() = EzeTapModel(
    headingText = headingText ?: "",
    logoUrl = logoUrl ?: "",
    uiData = uiData?.map { it.toUiData() } ?: emptyList()
)