package com.movies_api.movies_aplication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.movies_api.movies_aplication.R
import com.movies_api.movies_aplication.activity.main_activity.mvp.activity.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_movie.*
import kotlinx.android.synthetic.main.detail_movie.movie_description
import kotlinx.android.synthetic.main.detail_movie.movie_name

class DetailMovie : Fragment(){

    private var mContext: Context? = null

    private var mainActivity: MainActivity? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_movie, container, false)
        mainActivity = activity as MainActivity?
        assert(mainActivity != null)
        mContext = context

        return view
    }

    override fun onResume() {
        super.onResume()
        createMovie()
        setOnClickLkisteners()
    }



    fun removeFragment() {
        mainActivity!!.supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun createMovie(){

        movie_name.text= mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.title

        movie_language.text= mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.originalLanguage

        movie_description.text= mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.overview

        Picasso.get().load("https://image.tmdb.org/t/p/w92${mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.posterPath}")
            .into(movie_picture_fragment)

        movie_date.text= mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.releaseDate

        movie_vote.text= mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.voteCount.toString()

        movie_vote_rating.text= mainActivity!!
            .presenter.getMoviesDB().first()!!
            .results!!.get(mainActivity!!.presenter.indexMovie)!!.voteAverage.toString() + "/10"

    }

    fun setOnClickLkisteners(){
        btn_add.setOnClickListener {
            mainActivity!!.presenter.addMovieShopping_Cart(
                movie_name.text.toString(),
                movie_description.text.toString(),
                mainActivity!!
                    .presenter.getMoviesDB().first()!!
                    .results!!.get(mainActivity!!.presenter.indexMovie)!!.id,
                mainActivity!!
                    .presenter.getMoviesDB().first()!!
                    .results!!.get(mainActivity!!.presenter.indexMovie)!!.posterPath



            )
        }
    }

}