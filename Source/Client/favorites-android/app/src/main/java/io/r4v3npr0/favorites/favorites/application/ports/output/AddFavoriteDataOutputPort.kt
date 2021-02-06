package io.r4v3npr0.favorites.favorites.application.ports.output

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel

interface AddFavoriteDataOutputPort {
    fun addFavorite(favorite: FavoriteModel): Result<Boolean, Throwable>
}