package com.webecom.activity

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import com.webecom.R
import com.google.firebase.auth.FirebaseAuth





public class AppClass : Application() {
    private var context: Context? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        MultiDex.install(this)
        FirebaseApp.initializeApp(this);
        FacebookSdk.sdkInitialize(applicationContext)
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id))
        //AppEventsLogger.activateApp(this)
        try {
            FirebaseApp.initializeApp(applicationContext)
            //FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        } catch (e: IllegalStateException) {
            FirebaseApp.initializeApp(applicationContext)
            e.printStackTrace()
        }
    }
}