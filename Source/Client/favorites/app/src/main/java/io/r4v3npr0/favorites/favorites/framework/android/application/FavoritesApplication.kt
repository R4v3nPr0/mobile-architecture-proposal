package io.r4v3npr0.favorites.favorites.framework.android.application

import android.app.Application
import io.realm.Realm

class FavoritesApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}