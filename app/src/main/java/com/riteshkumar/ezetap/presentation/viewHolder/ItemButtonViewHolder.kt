package com.riteshkumar.ezetap.presentation.viewHolder

import com.riteshkumar.ezetap.databinding.ItemButtonBinding
import com.riteshkumar.ezetap.domain.model.UIData

class ItemButtonViewHolder(
    private val binding: ItemButtonBinding,
    private val onItemClicked: (() -> Unit)? = null
) : BaseViewHolder(binding.root) {

    override fun bind(item: UIData) {
        binding.btnSubmit.text = item.value
        binding.btnSubmit.setOnClickListener {
            onItemClicked?.invoke()
        }
    }
}