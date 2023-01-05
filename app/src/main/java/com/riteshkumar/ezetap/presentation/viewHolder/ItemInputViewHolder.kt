package com.riteshkumar.ezetap.presentation.viewHolder

import android.text.InputType
import androidx.core.widget.doOnTextChanged
import com.riteshkumar.ezetap.databinding.ItemEditTextBinding
import com.riteshkumar.ezetap.domain.model.UIData

class ItemInputViewHolder(
    private val binding: ItemEditTextBinding
) : BaseViewHolder(binding.root) {
    override fun bind(item: UIData) {
        binding.etUserInput.apply {
            inputType = getInputType(item)
            hint = item.hint
            doOnTextChanged { text, _, _, _ -> item.value = text.toString() }
        }
    }

    private fun getInputType(item: UIData) = when (item.key) {
        "text_phone" -> InputType.TYPE_CLASS_PHONE
        else -> InputType.TYPE_CLASS_TEXT
    }
}