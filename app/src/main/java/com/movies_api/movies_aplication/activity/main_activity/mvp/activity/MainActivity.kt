package com.movies_api.movies_aplication.activity.main_activity.mvp.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.movies_api.movies_aplication.R


import com.movies_api.movies_aplication.activity.main_activity.dagger.DaggerMainComponent
import com.movies_api.movies_aplication.activity.main_activity.dagger.MainModule
import com.movies_api.movies_aplication.activity.main_activity.mvp.presenter.MainPresenter
import com.movies_api.movies_aplication.activity.main_activity.mvp.view.MainView
import com.movies_api.movies_aplication.dagger.aplication.Aplication_test
import com.movies_api.movies_aplication.fragment.DetailMovie
import com.movies_api.movies_aplication.fragment.ShoppingCart

import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var activity: AppCompatActivity

    @Inject
    lateinit var view: MainView
    @Inject
    lateinit var presenter: MainPresenter
    @Inject
    lateinit var detailMovie: DetailMovie
    @Inject
    lateinit var shoppingCart: ShoppingCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDagger()
        activity = this
        presenter.check_permissionsRequest()
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        view.createRecyclerView()
        getMovies()
        view.updateShopping_cart(presenter.getMovieShopping_Cart().size)

    }

    private fun setDagger() {
        DaggerMainComponent.builder()
            .appComponent(Aplication_test().get(this).component())
            .mainModule(MainModule(this))
            .build().injectMainActivity(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> for (i in grantResults.indices) {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                    Toast.makeText(
                        this,
                        "Ha denegado el permiso, debes aceptarlo de lo contrario la app podria funciona de manera inadecuada",
                        Toast.LENGTH_LONG
                    ).show()
                    presenter.check_permissionsRequest()
                    break
                }
            }
        }
    }

    fun getMovies() {
        presenter.getMovies(1)

    }

    fun showFragmentDetailMovie(detailMovie: DetailMovie) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.parent_layout_main, detailMovie)
                .addToBackStack(null).commit()



    }

    fun showFragmentCart(shoppingCart: ShoppingCart) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.parent_layout_main, shoppingCart)
            .addToBackStack(null).commit()



    }

}
