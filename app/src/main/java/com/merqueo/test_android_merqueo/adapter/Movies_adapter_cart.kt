package com.merqueo.test_android_merqueo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity.MainActivity
import com.merqueo.test_android_merqueo.data_model.ResultShoppingCart
import com.squareup.picasso.Picasso


import io.realm.RealmResults
import kotlinx.android.synthetic.main.movie_card.view.movie_description
import kotlinx.android.synthetic.main.movie_card.view.movie_name
import kotlinx.android.synthetic.main.movie_card.view.movie_picture
import kotlinx.android.synthetic.main.movie_shooping_cart.view.*


class Movies_adapter_cart(private val activity: MainActivity, private val movies_db: RealmResults<ResultShoppingCart>) : RecyclerView.Adapter<Movies_adapter_cart.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_shooping_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val movies = movies_db[position]

            holder.movie_name.text = movies!!.originalTitle
            holder.movie_description.text = movies.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w92${movies.posterPath}").into(holder.movie_picture)
            holder.btn_remove.setOnClickListener {
                activity.presenter.clearMovieCartFromDB( position)
                activity.view.showToast("${holder.movie_name.text.toString()}, ${activity.resources.getString(R.string.remove_movie)}")

            }



        } catch (e: Exception) {
        }


    }

    override fun getItemCount(): Int {
        return movies_db.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val movie_name: TextView
        internal val movie_description: TextView
        internal val btn_remove : ImageView
        internal val movie_picture : ImageView


        init {
            movie_name = itemView.movie_name
            movie_description = itemView.movie_description
            btn_remove = itemView.btn_remove
            movie_picture= itemView.movie_picture

        }
    }


}
