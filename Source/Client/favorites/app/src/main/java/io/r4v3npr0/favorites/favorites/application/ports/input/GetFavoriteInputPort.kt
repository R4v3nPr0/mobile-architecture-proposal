package io.r4v3npr0.favorites.favorites.application.ports.input

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel

interface GetFavoriteInputPort {
    fun getFavorite(id: String): Result<FavoriteModel, Throwable>
}