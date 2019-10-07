package com.merqueo.test_android_merqueo.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity.MainActivity
import kotlinx.android.synthetic.main.shopping_cart.*


class ShoppingCart : Fragment(){
    private var mContext: Context? = null

    private var mainActivity: MainActivity? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.shopping_cart, container, false)
        mainActivity = activity as MainActivity?
        assert(mainActivity != null)
        mContext = context

        return view
    }

    override fun onResume() {
        super.onResume()
        createRecyclerView()
        setOnclickListeners()
    }



    fun removeFragment() {
        mainActivity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun setOnclickListeners(){
    empty_cart.setOnClickListener {
        mainActivity!!.presenter.clearMoviesCartFromDB()
    }

    }

    fun createRecyclerView(){
        recycler_view_movies_cart.layoutManager = LinearLayoutManager(activity)
        recycler_view_movies_cart.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view_movies_cart.adapter = mainActivity!!.presenter.createMovies_adapterShoppingCart(
            mainActivity!!,mainActivity!!.presenter.getMovieShopping_Cart())
    }

}