package io.r4v3npr0.favorites.favorites.framework.realm.adapter

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.framework.realm.converter.FavoritesConverter
import io.r4v3npr0.favorites.favorites.framework.realm.model.FavoriteRealmModel
import io.r4v3npr0.favorites.favorites.interfaceadapter.FavoritesDataGateway
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class RealmFavoritesDataGateway: FavoritesDataGateway {
    companion object {
        private const val FIELD_NAME_ID = "id"
    }

    private val realm = Realm.getDefaultInstance()

    override fun deleteFavorite(id: String): Result<String, Throwable> {
        var result: Result<String, Throwable>

        runBlocking(Dispatchers.Main) {
            val favorite = realm.where(FavoriteRealmModel::class.java).equalTo(FIELD_NAME_ID, id).findFirst()

            if (favorite != null) {
                result = Result.success(favorite.id)

                realm.beginTransaction()
                favorite.deleteFromRealm()
                realm.commitTransaction()
            } else {
                result = Result.failure(Throwable("Ocurrió un error en la base de datos"))
            }
        }

        return result
    }

    override fun getFavorite(id: String): Result<FavoriteModel, Throwable> {
        var result: Result<FavoriteModel, Throwable>

        runBlocking(Dispatchers.Main) {
            val favorite = realm
                .where(FavoriteRealmModel::class.java)
                .equalTo(FIELD_NAME_ID, id)
                .findFirst()

            result = if (favorite != null) {
                Result.success(FavoritesConverter.convertToApplicationModel(favorite))
            } else {
                Result.failure(Throwable("Ocurrió un error en la base de datos"))
            }
        }

        return result
    }

    override fun getFavorites(): Result<List<FavoriteModel>, Throwable> {
        var result: Result<List<FavoriteModel>, Throwable>

        runBlocking(Dispatchers.Main) {
            val favorites: MutableList<FavoriteModel> = mutableListOf()

            realm
                .where(FavoriteRealmModel::class.java)
                .findAll()
                .forEach {
                    favorites.add(FavoritesConverter.convertToApplicationModel(it))
                }

            result = Result.success(favorites)
        }

        return result
    }

    override fun isEmpty(): Boolean {
        var isEmpty: Boolean

        runBlocking(Dispatchers.Main) {
            isEmpty = realm.where(FavoriteRealmModel::class.java).count().toInt() == 0
        }

        return isEmpty
    }

    override fun modifyFavorite(favorite: FavoriteModel): Result<Boolean, Throwable> {
        var result: Result<Boolean, Throwable>

        runBlocking(Dispatchers.Main) {
            realm.apply {
                beginTransaction()
                insertOrUpdate(FavoritesConverter.convertToRealmModel(favorite))
                commitTransaction()
            }

            result = Result.success(true)
        }

        return result
    }

    override fun saveFavorites(favorites: List<FavoriteModel>): Result<Boolean, Throwable> {
        var result: Result<Boolean, Throwable>

        runBlocking(Dispatchers.Main) {
            favorites.forEach {
                realm.apply {
                    beginTransaction()
                    copyToRealm(FavoritesConverter.convertToRealmModel(it))
                    commitTransaction()
                }
            }

            result = Result.success(true)
        }

        return result
    }
}