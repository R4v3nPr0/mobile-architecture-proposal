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
    override fun modifyFavorite(favorite: FavoriteModel): Result<String, Throwable> {
        val modifyFavoriteServiceResult = modifyFavoriteServiceOutputPort.modifyFavorite(favorite)
        var result: Result<String, Throwable>

        if (modifyFavoriteServiceResult.isSuccess) {
            val success = modifyFavoriteServiceResult.result!!

            if (success) {
                val getFavoriteServiceResult = getFavoriteServiceOutputPort.getFavorite(favorite.id)

                result = if (getFavoriteServiceResult.isSuccess) {
                    val modifyFavoriteDataResult =
                        modifyFavoriteDataOutputPort.modifyFavorite(getFavoriteServiceResult.result!!)

                    if (modifyFavoriteDataResult.isSuccess) {
                        Result.success(getFavoriteServiceResult.result!!.id)
                    } else {
                        Result.failure(modifyFavoriteDataResult.failure!!)
                    }
                } else {
                    Result.failure(getFavoriteServiceResult.failure!!)
                }
            } else {
                result = Result.failure(Throwable("Ocurrió un error"))
            }
        } else {
            result = Result.failure(modifyFavoriteServiceResult.failure!!)
        }

        return result
    }
}