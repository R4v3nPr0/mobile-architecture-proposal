package io.r4v3npr0.favorites.favorites.application.usecase

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.GetFavoritesInputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoritesDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoritesServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.IsFavoritesEmptyDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.SaveFavoritesDataOutputPort

class GetFavoritesUseCase(
    private val getFavoritesDataOutputPort: GetFavoritesDataOutputPort,
    private val getFavoritesServiceOutputPort: GetFavoritesServiceOutputPort,
    private val isFavoritesEmptyDataOutputPort: IsFavoritesEmptyDataOutputPort,
    private val saveFavoritesDataOutputPort: SaveFavoritesDataOutputPort
): GetFavoritesInputPort
{
    override fun getFavorites(): Result<List<FavoriteModel>, Throwable> {
        return if (isFavoritesEmptyDataOutputPort.isEmpty()) {
            val getFavoritesServiceResult = getFavoritesServiceOutputPort.getFavorites()

             if (getFavoritesServiceResult.isSuccess) {
                val saveFavoritesDataResult =
                    saveFavoritesDataOutputPort.saveFavorites(getFavoritesServiceResult.result!!)

                if (saveFavoritesDataResult.isSuccess && saveFavoritesDataResult.result!!) {
                    Result.success(getFavoritesServiceResult.result!!)
                } else {
                    Result.failure(saveFavoritesDataResult.failure!!)
                }
            } else {
                Result.failure(getFavoritesServiceResult.failure!!)
            }
        } else {
            val getFavoritesDataResult = getFavoritesDataOutputPort.getFavorites()

            if (getFavoritesDataResult.isSuccess) {
                Result.success(getFavoritesDataResult.result!!)
            } else {
                Result.failure(getFavoritesDataResult.failure!!)
            }
        }
    }
}