package com.merqueo.test_android_merqueo.data_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ResultShoppingCart : RealmObject(){

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("genre_ids")
    @Expose
    var genreIds: RealmList<Int>? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

   

}