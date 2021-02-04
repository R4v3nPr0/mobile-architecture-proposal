package io.r4v3npr0.favorites.favorites.application.ports.output

import io.r4v3npr0.favorites.core.util.Result

interface DeleteFavoriteDataOutputPort {
    fun deleteFavorite(id: String): Result<String, Throwable>
}