package com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.main_activity.dagger.DaggerMainComponent
import com.merqueo.test_android_merqueo.activity.main_activity.dagger.MainModule
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.presenter.MainPresenter
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.view.MainView
import com.merqueo.test_android_merqueo.dagger.aplication.Aplication_test
import com.merqueo.test_android_merqueo.helpers.Constants
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var activity: AppCompatActivity

    @Inject
    lateinit var view: MainView
    @Inject
    lateinit var presenter: MainPresenter

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

}
