package io.r4v3npr0.favorites.favorites.framework.android.ui.addfavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.interfaceadapter.AddFavoriteViewState

class AddFavoriteViewModel: ViewModel(), AddFavoriteViewState {
    val favorite: MutableLiveData<FavoriteModel> = MutableLiveData()

    init {
        favorite.postValue(FavoriteModel())
    }

    override fun getFavorite() = favorite.value!!

    override fun setFavorite(favorite: FavoriteModel) {
        this.favorite.postValue(favorite)
    }
}