package io.r4v3npr0.favorites.favorites.interfaceadapter

interface ModifyFavoriteView {
    fun back()
    fun getAccountNumber(): String
    fun getAccountType(): Int
    fun getName(): String
}