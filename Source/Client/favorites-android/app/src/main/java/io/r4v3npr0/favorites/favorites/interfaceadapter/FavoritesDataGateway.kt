package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.ports.output.*

interface FavoritesDataGateway:
    AddFavoriteDataOutputPort,
    DeleteFavoriteDataOutputPort,
    IsFavoritesEmptyDataOutputPort,
    GetFavoriteDataOutputPort,
    GetFavoritesDataOutputPort,
    ModifyFavoriteDataOutputPort,
    SaveFavoritesDataOutputPort