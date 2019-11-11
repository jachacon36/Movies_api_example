package com.movies_api.movies_aplication.activity.main_activity.mvp.view

import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies_api.movies_aplication.R
import com.movies_api.movies_aplication.activity.main_activity.mvp.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.view.*

class MainView(val activity: MainActivity)  : FrameLayout(activity)  {

    init {
        View.inflate(context, R.layout.activity_main, this)
        setOnclickListeners()

    }

    fun createRecyclerView(){
        recycler_view_movies.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        recycler_view_movies.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view_movies.adapter = activity.presenter.createMovies_adapter(activity,activity.presenter.getMoviesDB())
    }

    fun updateShopping_cart(count: Int){
        cart_count.text = count.toString()
    }

    fun getShopping_cartCount() : Int{
        var count : String = cart_count.text.toString()
       return count.toInt()
    }

    fun setOnclickListeners(){
        cart_item.setOnClickListener {
            activity.showFragmentCart(activity.shoppingCart)
        }
    }

    fun showToast(text : String){
        Toast.makeText(activity, "$text", Toast.LENGTH_SHORT).show()
    }
}