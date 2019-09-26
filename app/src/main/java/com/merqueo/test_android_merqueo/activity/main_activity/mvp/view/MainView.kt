package com.merqueo.test_android_merqueo.activity.main_activity.mvp.view

import android.view.View
import android.widget.FrameLayout
import com.merqueo.test_android_merqueo.R
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity.MainActivity

class MainView(val activity: MainActivity)  : FrameLayout(activity)  {

    init {
        View.inflate(context, R.layout.activity_main, this)
    }
}