package io.r4v3npr0.favorites.favorites.framework.android.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.r4v3npr0.favorites.databinding.ActivityFavoritesItemFavoriteBinding
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.framework.android.base.RecyclerViewBase

class FavoritesAdapter(
    private val data: List<FavoriteModel>,
    private val layoutInflater: LayoutInflater,
    onItemLongClickListener: RecyclerViewBase.OnItemLongClickListener? = null
): RecyclerViewBase.AdapterBase<FavoritesAdapter.ViewHolder>(onItemLongClickListener)
{
    inner class ViewHolder(
        private val binding: ActivityFavoritesItemFavoriteBinding,
        itemView: View
    ): RecyclerViewBase.ViewHolderBase(itemView) {
        fun bind(favorite: FavoriteModel) = binding.apply {
            accountNumber = favorite.accountNumber
            name = favorite.name
        }
    }

    override fun getItemCount() = data.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityFavoritesItemFavoriteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, binding.root)
    }
}