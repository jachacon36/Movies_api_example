package com.merqueo.test_android_merqueo.activity.data_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject


open class Movies_model : RealmObject() {

    @SerializedName("page")
    @Expose
    private var page: Int? = null
    @SerializedName("total_results")
    @Expose
    private var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    private var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    private var results: RealmList<Result>? = null

    fun getPage(): Int? {
        return page
    }

    fun setPage(page: Int?) {
        this.page = page
    }

    fun getTotalResults(): Int? {
        return totalResults
    }

    fun setTotalResults(totalResults: Int?) {
        this.totalResults = totalResults
    }

    fun getTotalPages(): Int? {
        return totalPages
    }

    fun setTotalPages(totalPages: Int?) {
        this.totalPages = totalPages
    }

    fun getResults(): RealmList<Result>? {
        return results
    }

    fun setResults(results: RealmList<Result>) {
        this.results = results
    }
}