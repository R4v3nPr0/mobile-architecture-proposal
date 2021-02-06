package io.r4v3npr0.favorites.favorites.framework.realm.converter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.framework.realm.model.FavoriteRealmModel

class FavoritesConverter {
    companion object {
        @JvmStatic
        fun convertToApplicationModel(realmModel: FavoriteRealmModel) = FavoriteModel(
            realmModel.id,
            realmModel.accountNumber,
            realmModel.accountType,
            realmModel.name
        )

        @JvmStatic
        fun convertToRealmModel(applicationModel: FavoriteModel) = FavoriteRealmModel(
            applicationModel.id,
            applicationModel.accountNumber,
            applicationModel.accountType,
            applicationModel.name
        )
    }
}