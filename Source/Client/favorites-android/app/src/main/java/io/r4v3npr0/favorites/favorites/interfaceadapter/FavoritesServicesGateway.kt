package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.ports.output.AddFavoriteServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.DeleteFavoriteServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoriteServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.GetFavoritesServiceOutputPort
import io.r4v3npr0.favorites.favorites.application.ports.output.ModifyFavoriteServiceOutputPort

interface FavoritesServicesGateway:
    AddFavoriteServiceOutputPort,
    DeleteFavoriteServiceOutputPort,
    GetFavoriteServiceOutputPort,
    GetFavoritesServiceOutputPort,
    ModifyFavoriteServiceOutputPort