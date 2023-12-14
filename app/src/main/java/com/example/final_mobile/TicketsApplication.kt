package com.example.final_mobile

import android.app.Application
import com.example.final_mobile.data.AppContainer
import com.example.final_mobile.data.DefaultAppContainer

class TicketsApplication : Application() {
        /** AppContainer instance used by the rest of classes to obtain dependencies */
        lateinit var container: AppContainer
        override fun onCreate() {
            super.onCreate()
            container = DefaultAppContainer()
        }
    }
