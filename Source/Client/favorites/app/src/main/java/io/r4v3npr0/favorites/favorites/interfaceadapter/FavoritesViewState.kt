package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel

interface FavoritesViewState {
    fun getFavorites(): List<FavoriteModel>
    fun setFavorites(favorites: List<FavoriteModel>)
}