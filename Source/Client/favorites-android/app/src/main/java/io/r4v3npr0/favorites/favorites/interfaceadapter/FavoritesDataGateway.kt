package io.r4v3npr0.favorites.favorites.interfaceadapter

import io.r4v3npr0.favorites.favorites.application.ports.output.*

interface FavoritesDataGateway:
    AddFavoriteDataOutputPort,
    DeleteFavoriteDataOutputPort,
    DeleteFavoritesDataOutputPort,
    IsFavoritesEmptyDataOutputPort,
    GetFavoriteDataOutputPort,
    GetFavoritesDataOutputPort,
    ModifyFavoriteDataOutputPort,
    SaveFavoritesDataOutputPort