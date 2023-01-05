package com.riteshkumar.ezetap.presentation.viewHolder

import android.graphics.Paint.Style
import android.graphics.Typeface
import com.riteshkumar.ezetap.databinding.ItemLabelBinding
import com.riteshkumar.ezetap.domain.model.UIData

class InfoViewHolder(private val binding: ItemLabelBinding) : BaseViewHolder(binding.root) {

    override fun bind(item: UIData) {
        binding.title.text = item.value
        binding.title.setTypeface(null, Typeface.BOLD)
    }
}