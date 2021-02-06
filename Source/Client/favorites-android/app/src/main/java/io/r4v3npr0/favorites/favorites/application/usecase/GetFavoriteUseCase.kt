package io.r4v3npr0.favorites.favorites.application.usecase

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.GetFavoriteInputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoriteDataOutputPort

class GetFavoriteUseCase(
    private val getFavoriteDataOutputPort: GetFavoriteDataOutputPort
): GetFavoriteInputPort {
    override fun getFavorite(id: String): Result<FavoriteModel, Throwable> {
        val getFavoriteDataResult = getFavoriteDataOutputPort.getFavorite(id)

        return if (getFavoriteDataResult.isSuccess) {
            Result.success(getFavoriteDataResult.result!!)
        } else {
            Result.failure(getFavoriteDataResult.failure!!)
        }
    }
}