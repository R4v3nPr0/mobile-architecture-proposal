package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.ports.input.DeleteFavoriteInputPort
import io.r4v3npr0.favorites.favorites.application.ports.input.GetFavoritesInputPort

class FavoritesPresenterImpl(
    private val deleteFavoriteInputPort: DeleteFavoriteInputPort,
    private val getFavoritesInputPort: GetFavoritesInputPort,
    private val view: FavoritesView,
    private val viewState: FavoritesViewState
): FavoritesPresenter
{
    override fun onDeleteFavorite(position: Int) {
        val favorites = viewState.getFavorites()
        val favorite = favorites[position]
        val result = deleteFavoriteInputPort.deleteFavorite(favorite.id)

        if (result.isSuccess) {
            viewState.setFavorites(favorites.minus(favorite))
        } else {

        }
    }

    override fun onLoad() {
        val result = getFavoritesInputPort.getFavorites()

        if (result.isSuccess) {
            viewState.setFavorites(result.result!!)
        } else {

        }
    }

    override fun onModifyFavorite(position: Int) {
        view.showModifyFavorites(viewState.getFavorites()[position].id)
    }
}