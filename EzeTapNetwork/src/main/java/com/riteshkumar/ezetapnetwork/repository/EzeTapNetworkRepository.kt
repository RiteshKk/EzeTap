package com.riteshkumar.ezetapnetwork.repository

import com.riteshkumar.ezetapnetwork.network.EzeTapRemoteApi

class EzeTapNetworkRepository(
    private val ezeTapRemoteApi: EzeTapRemoteApi = EzeTapRemoteApi()
) {
    fun fetchCustomUI(url: String?) = ezeTapRemoteApi.fetchCustomUI(url)

    fun fetchImage(url: String?) = ezeTapRemoteApi.fetchImage(url)
}