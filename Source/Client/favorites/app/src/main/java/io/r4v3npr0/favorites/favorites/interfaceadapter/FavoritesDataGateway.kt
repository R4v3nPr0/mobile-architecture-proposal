package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.ports.output.*

interface FavoritesDataGateway:
    DeleteFavoriteDataOutputPort,
    IsFavoritesEmptyDataOutputPort,
    GetFavoriteDataOutputPort,
    GetFavoritesDataOutputPort,
    ModifyFavoriteDataOutputPort,
    SaveFavoritesDataOutputPort