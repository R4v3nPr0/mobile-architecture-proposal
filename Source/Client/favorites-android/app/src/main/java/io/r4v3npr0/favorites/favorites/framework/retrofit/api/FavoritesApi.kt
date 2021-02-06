package io.r4v3npr0.favorites.favorites.framework.retrofit.api

import io.r4v3npr0.favorites.favorites.framework.gson.model.*
import retrofit2.Call
import retrofit2.http.*

interface FavoritesApi {
    @POST("favorites/")
    fun addFavorite(@Body favorite: AddFavoriteRequestGsonModel): Call<AddFavoriteResponseGsonModel>

    @DELETE("favorites/{id}")
    fun deleteFavorite(@Path("id") id: String): Call<DeleteFavoriteResponseGsonModel>

    @GET("favorites/{id}")
    fun getFavorite(@Path("id") id: String): Call<GetFavoriteResponseGsonModel>

    @GET("favorites/")
    fun getFavorites(): Call<GetFavoritesResponseGsonModel>

    @PUT("favorites/{id}")
    fun modifyFavorite(@Body favorite: FavoriteGsonModel, @Path("id") id: String): Call<ModifyFavoriteResponseGsonModel>
}