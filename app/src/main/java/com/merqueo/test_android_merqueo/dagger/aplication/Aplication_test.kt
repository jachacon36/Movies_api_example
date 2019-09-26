package com.merqueo.test_android_merqueo.dagger.aplication

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.app.Service
import android.os.Build
import com.merqueo.test_android_merqueo.BuildConfig
import com.merqueo.test_android_merqueo.dagger.app_component.AppComponent
import com.merqueo.test_android_merqueo.dagger.app_component.DaggerAppComponent
import com.merqueo.test_android_merqueo.dagger.module.AppModule
import com.merqueo.test_android_merqueo.dagger.module.ContextModule
import io.realm.Realm
import io.realm.RealmConfiguration
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class Aplication_test : Application() {

    internal lateinit var appcomponent : AppComponent

    operator fun get(activity: Activity): Aplication_test {
        return activity.application as Aplication_test
    }

    operator fun get(fragment: Fragment): Aplication_test {
        return fragment.activity.application as Aplication_test
    }

    operator fun get(service: Service): Aplication_test {
        return service.application as Aplication_test
    }

    override fun onCreate() {
        super.onCreate()

        initRealmConfiguration()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {

                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }

                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, "RxMVP", message, t)
                }
            })
        }
        JodaTimeAndroid.init(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Timber.i("onCreate: Permisos para Android M y superior.")
        }
        appcomponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this)) // must be injected by dagger look in future.
            .appModule(AppModule(this))
            .build()
    }

    private fun initRealmConfiguration() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .name(Realm.DEFAULT_REALM_NAME)
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }



}