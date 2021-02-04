package io.r4v3npr0.favorites.favorites.framework.android.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.interfaceadapter.FavoritesViewState

class FavoritesViewModel: ViewModel(), FavoritesViewState {
    val favorites: MutableLiveData<List<FavoriteModel>> = MutableLiveData()

    override fun getFavorites() = favorites.value!!

    override fun setFavorites(favorites: List<FavoriteModel>) {
        this.favorites.postValue(favorites)
    }
}