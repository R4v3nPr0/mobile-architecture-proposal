package io.r4v3npr0.favorites.favorites.application.converter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.entity.FavoriteEntity

class FavoritesConverter {
    companion object {
        @JvmStatic
        fun convertToApplicationModel(entity: FavoriteEntity) = FavoriteModel(
            entity.id,
            entity.accountNumber,
            entity.accountType.value,
            entity.name
        )
    }
}