package com.example.rbcassignment.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssignmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}