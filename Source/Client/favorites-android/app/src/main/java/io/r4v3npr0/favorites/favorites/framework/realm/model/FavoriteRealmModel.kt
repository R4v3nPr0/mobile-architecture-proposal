package io.r4v3npr0.favorites.favorites.framework.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteRealmModel(
    @PrimaryKey var id: String = "",
    var accountNumber: String = "",
    var accountType: Int = 0,
    var name: String = ""
): RealmObject()