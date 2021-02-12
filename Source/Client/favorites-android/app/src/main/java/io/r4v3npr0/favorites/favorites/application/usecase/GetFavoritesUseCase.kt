package io.r4v3npr0.favorites.favorites.application.usecase

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.GetFavoritesInputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.DeleteFavoritesDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoritesDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoritesServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.IsFavoritesEmptyDataOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.SaveFavoritesDataOutputPort

class GetFavoritesUseCase(
    private val deleteFavoritesDataOutputPort: DeleteFavoritesDataOutputPort,
    private val getFavoritesDataOutputPort: GetFavoritesDataOutputPort,
    private val getFavoritesServiceOutputPort: GetFavoritesServiceOutputPort,
    private val isFavoritesEmptyDataOutputPort: IsFavoritesEmptyDataOutputPort,
    private val saveFavoritesDataOutputPort: SaveFavoritesDataOutputPort
): GetFavoritesInputPort
{
    override fun getFavorites(reload: Boolean): Result<List<FavoriteModel>, Throwable> {
        val isFavoritesEmptyDataResult = isFavoritesEmptyDataOutputPort.isEmpty()

        return if (isFavoritesEmptyDataResult.isSuccess) {
            if (isFavoritesEmptyDataResult.result!! or reload) {
                val getFavoritesServiceResult = getFavoritesServiceOutputPort.getFavorites()

                if (getFavoritesServiceResult.isSuccess) {
                    deleteFavoritesDataOutputPort.deleteFavorites()

                    val saveFavoritesDataResult = saveFavoritesDataOutputPort.saveFavorites(getFavoritesServiceResult.result!!)

                    if (saveFavoritesDataResult.isSuccess) {
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
        } else {
            Result.failure(isFavoritesEmptyDataResult.failure!!)
        }
    }
}