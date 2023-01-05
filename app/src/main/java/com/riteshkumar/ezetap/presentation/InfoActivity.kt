package com.riteshkumar.ezetap.presentation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.riteshkumar.ezetap.common.INTENT_TAG
import com.riteshkumar.ezetap.databinding.ActivityInfoBinding
import com.riteshkumar.ezetap.domain.model.EzeTapModel
import com.riteshkumar.ezetap.domain.model.UIData
import com.riteshkumar.ezetap.presentation.adapter.InfoAdapter

class InfoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val model: EzeTapModel? =
            if (Build.VERSION.SDK_INT >= 33) {
                intent?.getParcelableExtra(INTENT_TAG, EzeTapModel::class.java)
            } else {
                intent?.getParcelableExtra(INTENT_TAG)
            }
        val adapter = InfoAdapter()
        adapter.updateList(model?.uiData as ArrayList<UIData>)
        binding.recyclerView.adapter = adapter
    }
}