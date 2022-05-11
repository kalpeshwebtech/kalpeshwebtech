package com.example.flipcart.activity

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

public class AppClass : Application() {
    private var context: Context? = null


    override fun onCreate() {
        super.onCreate()
        context = this
        MultiDex.install(this)
       /* try {
            FirebaseApp.initializeApp(applicationContext)
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        } catch (e: IllegalStateException) {
            FirebaseApp.initializeApp(applicationContext)
            e.printStackTrace()
        }*/
    }
}