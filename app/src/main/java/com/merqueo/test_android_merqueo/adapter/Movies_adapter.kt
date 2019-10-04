package com.merqueo.test_android_merqueo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.data_model.Movies_model

import io.realm.RealmResults
import kotlinx.android.synthetic.main.movie_card.view.*


class Movies_adapter(private val movies_db: RealmResults<Movies_model>) : RecyclerView.Adapter<Movies_adapter.ViewHolder>() {

    var stops_ : List<Movies_model>

    init {
        this.stops_ = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val movies = movies_db[position]

            holder.movie_name.text = movies!!.results?.get(position)?.originalTitle
            holder.movie_description.text = movies!!.results?.get(position)?.overview

        } catch (e: Exception) {
        }


    }

    override fun getItemCount(): Int {
        return movies_db.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val movie_name: TextView
        internal val movie_description: TextView




        init {
            movie_name = itemView.movie_name
            movie_description = itemView.movie_description



        }
    }


}
