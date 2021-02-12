package io.r4v3npr0.favorites.favorites.application.ports.input

import io.r4v3npr0.favorites.core.util.Result

interface DeleteFavoriteInputPort {
    fun deleteFavorite(id: String): Result<Boolean, Throwable>
}