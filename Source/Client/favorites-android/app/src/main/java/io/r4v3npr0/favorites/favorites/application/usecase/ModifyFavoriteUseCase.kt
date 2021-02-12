package io.r4v3npr0.favorites.favorites.application.usecase

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.ModifyFavoriteInputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoriteServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.ModifyFavoriteDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.ModifyFavoriteServiceOutputPort

class ModifyFavoriteUseCase(
    private val getFavoriteServiceOutputPort: GetFavoriteServiceOutputPort,
    private val modifyFavoriteDataOutputPort: ModifyFavoriteDataOutputPort,
    private val modifyFavoriteServiceOutputPort: ModifyFavoriteServiceOutputPort
): ModifyFavoriteInputPort {
    override fun modifyFavorite(favorite: FavoriteModel): Result<Boolean, Throwable> {
        val modifyFavoriteServiceResult = modifyFavoriteServiceOutputPort.modifyFavorite(favorite)

        return if (modifyFavoriteServiceResult.isSuccess) {
            val getFavoriteServiceResult = getFavoriteServiceOutputPort.getFavorite(favorite.id)

            if (getFavoriteServiceResult.isSuccess) {
                val modifyFavoriteDataResult = modifyFavoriteDataOutputPort.modifyFavorite(getFavoriteServiceResult.result!!)

                if (modifyFavoriteDataResult.isSuccess) {
                    Result.success(true)
                } else {
                    Result.failure(modifyFavoriteDataResult.failure!!)
                }
            } else {
                Result.failure(getFavoriteServiceResult.failure!!)
            }
        } else {
            Result.failure(modifyFavoriteServiceResult.failure!!)
        }
    }
}