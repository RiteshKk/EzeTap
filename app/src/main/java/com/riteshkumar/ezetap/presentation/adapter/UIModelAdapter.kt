package com.riteshkumar.ezetap.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riteshkumar.ezetap.R
import com.riteshkumar.ezetap.databinding.ItemButtonBinding
import com.riteshkumar.ezetap.databinding.ItemEditTextBinding
import com.riteshkumar.ezetap.databinding.ItemLabelBinding
import com.riteshkumar.ezetap.domain.model.UIData
import com.riteshkumar.ezetap.presentation.viewHolder.BaseViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.ItemButtonViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.ItemInputViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.ItemLabelViewHolder

class UIModelAdapter(
    private val onItemClicked: (() -> Unit)? = null
) : RecyclerView.Adapter<BaseViewHolder>() {

    val itemList: ArrayList<UIData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder: BaseViewHolder = when (viewType) {
            R.layout.item_label -> {
                val viewBinding =
                    ItemLabelBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ItemLabelViewHolder(viewBinding)
            }
            R.layout.item_edit_text -> {
                val viewBinding =
                    ItemEditTextBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ItemInputViewHolder(viewBinding)
            }
            R.layout.item_button -> {
                val viewBinding =
                    ItemButtonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ItemButtonViewHolder(viewBinding, onItemClicked)
            }
            else -> {
                throw java.lang.UnsupportedOperationException()
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val model: UIData = itemList[position]
        when (holder) {
            is ItemLabelViewHolder -> {
                model.let {
                    holder.bind(model)
                }
            }
            is ItemInputViewHolder -> {
                model.let { holder.bind(model) }
            }
            is ItemButtonViewHolder -> {
                model.let {
                    holder.bind(model)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item: UIData = itemList[position]
        return when (item.uiType) {
            "label" -> R.layout.item_label
            "edittext" -> R.layout.item_edit_text
            "button" -> R.layout.item_button
            else -> -1
        }
    }

    override fun getItemCount() = itemList.size

    fun updateList(list: ArrayList<UIData>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}