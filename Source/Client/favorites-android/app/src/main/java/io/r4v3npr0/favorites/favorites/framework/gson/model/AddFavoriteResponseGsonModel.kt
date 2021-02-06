package io.r4v3npr0.favorites.favorites.framework.gson.model

class AddFavoriteResponseGsonModel(val favorite: Favorite, val success: Boolean?) {
    data class Favorite(val id: String?)


}