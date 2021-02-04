package io.r4v3npr0.favorites.favorites.interfaceadapter

interface ModifyFavoritePresenter {
    fun onAccountNumberTextChange()
    fun onLoad(id: String)
    fun onNameTextChange()
    fun onSave()
}