package io.r4v3npr0.favorites.favorites.framework.retrofit.adapter

import io.r4v3npr0.favorites.core.util.Result
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.framework.gson.converter.FavoritesConverter
import io.r4v3npr0.favorites.favorites.framework.retrofit.api.FavoritesApi
import io.r4v3npr0.favorites.favorites.interfaceadapter.FavoritesServicesGateway
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFavoritesServicesGateway: FavoritesServicesGateway {
    private val favoritesApi: FavoritesApi
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://23.227.167.38/")
            .build()

        favoritesApi = retrofit.create(FavoritesApi::class.java)
    }

    override fun deleteFavorite(id: String): Result<String, Throwable> {
        val call = favoritesApi.deleteFavorite(id)

        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                val success = response.body()?.success ?: false

                if (success) {
                    Result.success(id)
                } else {
                    Result.failure(Throwable("Ocurrió un error al consultar el servicio"))
                }
            } else {
                Result.failure(Throwable("Ocurrió un error al consultar el servicio"))
            }
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override fun getFavorite(id: String): Result<FavoriteModel, Throwable> {
        val call = favoritesApi.getFavorite(id)

        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                val favorite = FavoriteModel(
                    response.body()?.id!!,
                    response.body()?.accountNumber!!,
                    response.body()?.accountType!!,
                    response.body()?.name!!
                )

                Result.success(favorite)
            } else {
                Result.failure(Throwable("Ocurrió un error al consultar el servicio"))
            }
        } catch (exception: Throwable) {
            Result.failure(Throwable("Ocurrió un error al consultar el servicio", exception))
        }
    }

    override fun getFavorites(): Result<List<FavoriteModel>, Throwable> {
        val call = favoritesApi.getFavorites()

        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                val favorites: MutableList<FavoriteModel> = mutableListOf()

                response.body()?.favorites?.forEach {
                    favorites.add(FavoritesConverter.convertToApplicationModel(it))
                }

                Result.success(favorites)
            } else {
                Result.failure(Throwable("Ocurrió un error al consultar el servicio"))
            }
        } catch (exception: Throwable) {
            Result.failure(Throwable("Ocurrió un error al consultar el servicio", exception))
        }
    }

    override fun modifyFavorite(favorite: FavoriteModel): Result<Boolean, Throwable> {
        val call = favoritesApi.modifyFavorite(FavoritesConverter.convertToGsonModel(favorite), favorite.id)

        return try {
            val response = call.execute()

            if (response.isSuccessful) {
                Result.success(response.body()?.success!!)
            } else {
                Result.failure(Throwable("Ocurrió un error al consultar el servicio"))
            }
        } catch (exception: Throwable) {
            Result.failure(Throwable("Ocurrió un error al consultar el servicio", exception))
        }
    }
}