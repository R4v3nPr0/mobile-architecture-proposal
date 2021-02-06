package io.r4v3npr0.favorites.favorites.framework.gson.converter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.framework.gson.model.AddFavoriteRequestGsonModel
import io.r4v3npr0.favorites.favorites.framework.gson.model.FavoriteGsonModel

class FavoritesConverter {
    companion object {
        @JvmStatic
        fun convertToApplicationModel(gsonModel: FavoriteGsonModel) = FavoriteModel(
            gsonModel.id!!,
            gsonModel.accountNumber!!,
            gsonModel.accountType!!,
            gsonModel.name!!
        )

        @JvmStatic
        fun convertToGsonModel(applicationModel: FavoriteModel) = FavoriteGsonModel(
            applicationModel.id,
            applicationModel.accountNumber,
            applicationModel.accountType,
            applicationModel.name
        )

        @JvmStatic
        fun convertToAddFavoriteRequestModel(applicationModel: FavoriteModel) = AddFavoriteRequestGsonModel(
            applicationModel.accountNumber,
            applicationModel.accountType,
            applicationModel.name
        )
    }
}