package com.riteshkumar.ezetap.presentation.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.riteshkumar.ezetap.domain.model.UIData

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: UIData)
}