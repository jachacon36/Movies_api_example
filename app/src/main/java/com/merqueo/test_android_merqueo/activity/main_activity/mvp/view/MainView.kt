package com.merqueo.test_android_merqueo.activity.main_activity.mvp.view

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.view.*

class MainView(val activity: MainActivity)  : FrameLayout(activity)  {

    init {
        View.inflate(context, R.layout.activity_main, this)


    }

    fun createRecyclerView(){
        recycler_view_movies.layoutManager = LinearLayoutManager(activity)
        recycler_view_movies.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view_movies.adapter = activity.presenter.createAddres_adapter(activity,activity.presenter.getMoviesDB())
    }

    fun updateShopping_cart(count: Int){
        cart_count.text = count.toString()
    }
}