package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.ports.input.AddFavoriteInputPort

class AddFavoritePresenterImpl(
    private val addFavoriteInputPort: AddFavoriteInputPort,
    private val view: AddFavoriteView,
    private val viewState: AddFavoriteViewState
): AddFavoritePresenter {
    override fun onAccountNumberTextChange() {
        val accountNumber = view.getAccountNumber()
        val favorite = viewState.getFavorite()
        viewState.setFavorite(
            FavoriteModel(
                favorite.id,
                accountNumber,
                favorite.accountType,
                favorite.name
            )
        )
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

    override fun onNameTextChange() {
        val name = view.getName()
        val favorite = viewState.getFavorite()
        viewState.setFavorite(
            FavoriteModel(
                favorite.id,
                favorite.accountNumber,
                favorite.accountType,
                name
            )
        )
    }

    override fun onSave() {
        val favorite = viewState.getFavorite()

        val addFavoriteResult = addFavoriteInputPort.addFavorite(favorite)

        if (addFavoriteResult.isSuccess) {
            view.back()
        } else {

        }
    }
}