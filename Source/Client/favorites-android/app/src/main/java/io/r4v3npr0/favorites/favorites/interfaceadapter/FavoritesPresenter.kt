package io.r4v3npr0.favorites.favorites.interfaceadapter

interface FavoritesPresenter {
    fun onAdd()
    fun onDeleteFavorite(position: Int)
    fun onLoad()
    fun onModifyFavorite(position: Int)
    fun onReload()
}