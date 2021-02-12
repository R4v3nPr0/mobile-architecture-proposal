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
    override fun onAdd() {
        view.showAddFavorite()
    }

    override fun onDeleteFavorite(position: Int) {
        val favorites = viewState.getFavorites()
        val favorite = favorites[position]
        val deleteFavoritesResult = deleteFavoriteInputPort.deleteFavorite(favorite.id)

        if (deleteFavoritesResult.isSuccess) {
            viewState.setFavorites(favorites.minus(favorite))
        } else {

        }
    }

    override fun onLoad() {
        val getFavoritesResult = getFavoritesInputPort.getFavorites(false)

        if (getFavoritesResult.isSuccess) {
            viewState.setFavorites(getFavoritesResult.result!!)
        } else {

        }
    }

    override fun onModifyFavorite(position: Int) {
        view.showModifyFavorites(viewState.getFavorites()[position].id)
    }

    override fun onReload() {
        val getFavoritesResult = getFavoritesInputPort.getFavorites(true)

        if (getFavoritesResult.isSuccess) {
            viewState.setFavorites(getFavoritesResult.result!!)
        } else {

        }
    }
}