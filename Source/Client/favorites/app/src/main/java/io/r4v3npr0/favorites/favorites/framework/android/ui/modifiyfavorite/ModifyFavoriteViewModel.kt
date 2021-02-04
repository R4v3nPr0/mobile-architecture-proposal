package io.r4v3npr0.favorites.favorites.framework.android.ui.modifiyfavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.interfaceadapter.ModifyFavoriteViewState

class ModifyFavoriteViewModel: ViewModel(), ModifyFavoriteViewState {
    val favorite: MutableLiveData<FavoriteModel> = MutableLiveData()

    override fun getFavorite() = favorite.value!!

    override fun setFavorite(favorite: FavoriteModel) {
        this.favorite.postValue(favorite)
    }
}