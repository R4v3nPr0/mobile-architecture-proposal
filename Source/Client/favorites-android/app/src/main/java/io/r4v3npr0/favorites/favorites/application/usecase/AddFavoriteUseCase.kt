package io.r4v3npr0.favorites.favorites.application.usecase

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.AddFavoriteInputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.AddFavoriteDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.AddFavoriteServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoriteServiceOutputPort

class AddFavoriteUseCase(
    private val addFavoriteDataOutputPort: AddFavoriteDataOutputPort,
    private val addFavoriteServiceOutputPort: AddFavoriteServiceOutputPort,
    private val getFavoriteServiceOutputPort: GetFavoriteServiceOutputPort
): AddFavoriteInputPort {
    override fun addFavorite(favorite: FavoriteModel): Result<Boolean, Throwable> {
        val addFavoriteServiceResult = addFavoriteServiceOutputPort.addFavorite(favorite)

        if (addFavoriteServiceResult.isSuccess) {
            val id = addFavoriteServiceResult.result!!

            val getFavoriteServiceResult = getFavoriteServiceOutputPort.getFavorite(id)

            return if (getFavoriteServiceResult.isSuccess) {
                val favorite = getFavoriteServiceResult.result!!

                val addFavoriteDataResult = addFavoriteDataOutputPort.addFavorite(favorite)

                if (addFavoriteDataResult.isSuccess) {
                    Result.success(true)
                } else {
                    Result.failure(addFavoriteDataResult.failure!!)
                }
            } else {
                Result.failure(getFavoriteServiceResult.failure!!)
            }
        } else {
            return Result.failure(addFavoriteServiceResult.failure!!)
        }
    }
}