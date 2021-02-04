package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.ports.input.GetFavoritesInputPort

interface FavoritesPresenter {
    fun onDeleteFavorite(position: Int)
    fun onLoad()
    fun onModifyFavorite(position: Int)
    fun onReload()
}