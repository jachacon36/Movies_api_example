package com.movies_api.movies_aplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.movies_api.movies_aplication.R
import com.movies_api.movies_aplication.data_model.Movies_model
import com.movies_api.movies_aplication.activity.main_activity.mvp.activity.MainActivity
import com.squareup.picasso.Picasso


import io.realm.RealmResults
import kotlinx.android.synthetic.main.movie_card.view.*


class Movies_adapter(private val activity: MainActivity, private val movies_db: RealmResults<Movies_model>) : RecyclerView.Adapter<Movies_adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val movies = movies_db[position]

            holder.movie_name.text = movies!!.results?.get(position)?.originalTitle
            holder.movie_description.text = movies.results?.get(position)?.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w92${movies.results?.get(position)?.posterPath}").into(holder.movie_picture)
            holder.btn_add.setOnClickListener {

                activity.presenter.addMovieShopping_Cart(
                    movies.results?.get(position)?.originalTitle,
                    movies.results?.get(position)?.overview,
                    movies.results?.get(position)?.id,
                    movies.results?.get(position)?.posterPath

                )

            }

            holder.movie_picture.setOnClickListener {
                activity.presenter.indexMovie = position
                activity.showFragmentDetailMovie(activity.detailMovie)
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
        internal val btn_add : ImageView
        internal val movie_picture : ImageView


        init {
            movie_name = itemView.movie_name
            movie_description = itemView.movie_description
            btn_add = itemView.btn_add
            movie_picture= itemView.movie_picture

        }
    }


}
