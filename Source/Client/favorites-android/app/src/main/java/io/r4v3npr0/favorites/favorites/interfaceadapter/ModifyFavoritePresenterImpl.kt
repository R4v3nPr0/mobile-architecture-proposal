package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.GetFavoriteInputPort
import io.r4v3npr0.favorites.favorites.application.ports.input.ModifyFavoriteInputPort

class ModifyFavoritePresenterImpl(
    private val getFavoriteInputPort: GetFavoriteInputPort,
    private val modifyFavoriteInputPort: ModifyFavoriteInputPort,
    private val view: ModifyFavoriteView,
    private val viewState: ModifyFavoriteViewState
): ModifyFavoritePresenter {
    override fun onAccountNumberTextChange() {
        var favorite = viewState.getFavorite()

        viewState.setFavorite(FavoriteModel(
            favorite.id,
            view.getAccountNumber(),
            favorite.accountType,
            favorite.name
        ))
    }

    override fun onAccountTypeChange() {
        val favorite = viewState.getFavorite()

        viewState.setFavorite(FavoriteModel(
            favorite.id,
            favorite.accountNumber,
            view.getAccountType(),
            favorite.name
        ))
    }

    override fun onLoad(id: String) {
        val result = getFavoriteInputPort.getFavorite(id)

        if (result.isSuccess) {
            val favorite = result.result!!

            viewState.setFavorite(favorite)
        } else {

        }
    }

    override fun onNameTextChange() {
        var favorite = viewState.getFavorite()

        viewState.setFavorite(FavoriteModel(
            favorite.id,
            favorite.accountNumber,
            favorite.accountType,
            view.getName()
        ))
    }

    override fun onSave() {
        val result = modifyFavoriteInputPort.modifyFavorite(viewState.getFavorite())

        if (result.isSuccess) {
            view.back()
        } else {

        }
    }
}