package com.riteshkumar.ezetap.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riteshkumar.ezetap.R
import com.riteshkumar.ezetap.databinding.ItemButtonBinding
import com.riteshkumar.ezetap.databinding.ItemEditTextBinding
import com.riteshkumar.ezetap.databinding.ItemLabelBinding
import com.riteshkumar.ezetap.domain.model.UIData
import com.riteshkumar.ezetap.presentation.adapter.ViewType.INFO
import com.riteshkumar.ezetap.presentation.viewHolder.BaseViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.InfoViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.ItemButtonViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.ItemInputViewHolder
import com.riteshkumar.ezetap.presentation.viewHolder.ItemLabelViewHolder

class InfoAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val itemList: ArrayList<UIData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewBinding =
            ItemLabelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        val viewHolder: BaseViewHolder = when (viewType) {
            ViewType.LABEL.ordinal -> ItemLabelViewHolder(viewBinding)
            INFO.ordinal -> InfoViewHolder(viewBinding)
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
            is InfoViewHolder -> {
                model.let {
                    holder.bind(model)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item: UIData = itemList[position]
        return when (item.uiType) {
            "label" -> ViewType.LABEL.ordinal
            else -> INFO.ordinal
        }
    }

    override fun getItemCount() = itemList.size

    fun updateList(list: ArrayList<UIData>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}

enum class ViewType {
    LABEL,
    INFO
}