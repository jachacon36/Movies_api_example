package com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.main_activity.dagger.DaggerMainComponent
import com.merqueo.test_android_merqueo.activity.main_activity.dagger.MainModule
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.presenter.MainPresenter
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.view.MainView
import com.merqueo.test_android_merqueo.dagger.aplication.Aplication_test
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
        setContentView(view)
    }

    private fun setDagger() {
        DaggerMainComponent.builder()
            .appComponent(Aplication_test().get(this).component())
            .mainModule(MainModule(this))
            .build().injectMainActivity(this)
    }

}
