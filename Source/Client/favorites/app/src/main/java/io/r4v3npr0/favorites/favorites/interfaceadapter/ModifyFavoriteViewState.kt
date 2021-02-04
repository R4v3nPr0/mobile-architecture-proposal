package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel

interface ModifyFavoriteViewState {
    fun getFavorite(): FavoriteModel
    fun setFavorite(favorite: FavoriteModel)
}