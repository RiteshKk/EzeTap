package com.riteshkumar.ezetap.presentation.viewHolder

import com.riteshkumar.ezetap.databinding.ItemLabelBinding
import com.riteshkumar.ezetap.domain.model.UIData

class ItemLabelViewHolder(private val binding: ItemLabelBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: UIData) {
        binding.title.text = item.value
    }
}