package com.riteshkumar.ezetap.presentation

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.riteshkumar.ezetap.R
import com.riteshkumar.ezetap.common.INTENT_TAG
import com.riteshkumar.ezetap.common.Resource.Failure
import com.riteshkumar.ezetap.common.Resource.Loading
import com.riteshkumar.ezetap.common.Resource.Success
import com.riteshkumar.ezetap.databinding.ActivityMainBinding
import com.riteshkumar.ezetap.domain.model.EzeTapModel
import com.riteshkumar.ezetap.domain.model.UIData
import com.riteshkumar.ezetap.presentation.adapter.UIModelAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[EzeTapViewModel::class.java]
    }
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel.fetchUiData()
        setImagePlaceHolder()
        observeLiveData()
    }

    private fun setImagePlaceHolder() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
        viewModel.setPlaceHolderImage(bitmap)
    }

    private fun observeLiveData() {
        viewModel.responseLiveData.observe(this) { it ->
            when (it) {
                is Failure -> {
                    binding.progressLayout.isVisible = false
                    it.message?.let { it1 -> Snackbar.make(binding.root, it1, Snackbar.LENGTH_LONG).show() }
                }
                is Loading -> {
                    binding.progressLayout.isVisible = true
                }
                is Success -> {
                    binding.progressLayout.isVisible = false
                    viewModel.fetchImage(it.data?.logoUrl)
                    val adapter = UIModelAdapter {
                        val list = (binding.recyclerView.adapter as UIModelAdapter).itemList
                        list.dropLast(1).let { newList ->
                            val model = EzeTapModel(
                                uiData = newList
                            )
                            startActivity(
                                Intent(this, InfoActivity::class.java)
                                    .apply {
                                        putExtra(INTENT_TAG, model)
                                    }
                            )
                        }
                    }
                    adapter.updateList(it.data?.uiData as ArrayList<UIData>)
                    binding.apply {
                        title.text = it.data.headingText
                        recyclerView.adapter = adapter
                    }
                }
            }
        }

        viewModel.bitmapLiveData.observe(this) {
            binding.logo.setImageBitmap(it)
        }
    }
}