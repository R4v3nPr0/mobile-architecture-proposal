package io.r4v3npr0.favorites.favorites.framework.android.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewBase {
    abstract class AdapterBase<T: ViewHolderBase> (
        private val onItemLongClickListener: OnItemLongClickListener? = null
    ): RecyclerView.Adapter<T>() {
        override fun onBindViewHolder(holder: T, position: Int) {
            holder.setOnItemLongClickListener(onItemLongClickListener, position)
        }
    }

    abstract class ViewHolderBase(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setOnItemLongClickListener(
            onItemLongClickListener: OnItemLongClickListener?,
            position: Int
        ) {
            itemView.setOnLongClickListener {
                onItemLongClickListener?.onItemLongClick(position)
                true
            }
        }
    }

    fun interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }
}