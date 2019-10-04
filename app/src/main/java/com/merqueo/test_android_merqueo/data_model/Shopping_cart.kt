package com.merqueo.test_android_merqueo.data_model

import io.realm.RealmList
import io.realm.RealmObject

open class Shopping_cart : RealmObject(){

    var movies: RealmList<Int>? = null
}