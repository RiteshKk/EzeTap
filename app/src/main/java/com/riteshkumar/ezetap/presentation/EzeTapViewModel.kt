package com.riteshkumar.ezetap.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.riteshkumar.ezetap.common.GENERIC_ERROR_MESSAGE
import com.riteshkumar.ezetap.common.Resource
import com.riteshkumar.ezetap.common.Resource.Failure
import com.riteshkumar.ezetap.common.Resource.Loading
import com.riteshkumar.ezetap.common.Resource.Success
import com.riteshkumar.ezetap.common.URL
import com.riteshkumar.ezetap.data.dto.EzeTapModelDto
import com.riteshkumar.ezetap.data.dto.toEzeTapModel
import com.riteshkumar.ezetap.domain.model.EzeTapModel
import com.riteshkumar.ezetapnetwork.repository.EzeTapNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EzeTapViewModel(
    private var ezeTapNetworkRepository: EzeTapNetworkRepository = EzeTapNetworkRepository()
) : ViewModel() {

    private val _responseLiveData: MutableLiveData<Resource<EzeTapModel>> = MutableLiveData()
    val responseLiveData: LiveData<Resource<EzeTapModel>>
        get() = _responseLiveData

    private var _bitmapLiveData: MutableLiveData<Bitmap> = MutableLiveData()
    val bitmapLiveData: LiveData<Bitmap>
        get() = _bitmapLiveData

    fun fetchUiData() {
        viewModelScope.launch(Dispatchers.IO) {
            _responseLiveData.postValue(Loading())
            kotlin.runCatching {
                val response = ezeTapNetworkRepository.fetchCustomUI(URL)
                val gson = Gson()
                val ezeTapModel: EzeTapModelDto =
                    gson.fromJson(response.toString(), EzeTapModelDto::class.java)
                _responseLiveData.postValue(Success(ezeTapModel.toEzeTapModel()))
            }.onFailure {
                _responseLiveData.postValue(
                    Failure(
                        message = it.localizedMessage ?: GENERIC_ERROR_MESSAGE
                    )
                )
            }
        }
    }

    fun fetchImage(url: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val arrayBitmap = ezeTapNetworkRepository.fetchImage(url)
                val bitmap = BitmapFactory.decodeByteArray(
                    arrayBitmap, 0, arrayBitmap?.size ?: 0
                )
                _bitmapLiveData.postValue(bitmap)
            }.onFailure {
                Log.e("fetchImage", "Error->${it.message}")
            }
        }
    }

    fun setPlaceHolderImage(bitmap: Bitmap?) {
        bitmap?.let {
            _bitmapLiveData.value = it
        }
    }
}