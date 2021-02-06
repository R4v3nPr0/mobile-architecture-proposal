package io.r4v3npr0.favorites.favorites.application.usecase

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.ports.input.DeleteFavoriteInputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.DeleteFavoriteDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.DeleteFavoriteServiceOutputPort

class DeleteFavoriteUseCase(
    private val deleteFavoriteDataOutputPort: DeleteFavoriteDataOutputPort,
    private val deleteFavoriteServiceOutputPort: DeleteFavoriteServiceOutputPort
): DeleteFavoriteInputPort {
    override fun deleteFavorite(id: String): Result<String, Throwable> {
        val deleteFavoriteServiceResult = deleteFavoriteServiceOutputPort.deleteFavorite(id)
        var result: Result<String, Throwable>

        result = if (deleteFavoriteServiceResult.isSuccess) {
            val deleteFavoriteDataResult = deleteFavoriteDataOutputPort.deleteFavorite(id)

            if (deleteFavoriteDataResult.isSuccess) {
                Result.success(deleteFavoriteDataResult.result!!)
            } else {
                Result.failure(deleteFavoriteDataResult.failure!!)
            }
        } else {
            Result.failure(deleteFavoriteServiceResult.failure!!)
        }

        return result
    }
}