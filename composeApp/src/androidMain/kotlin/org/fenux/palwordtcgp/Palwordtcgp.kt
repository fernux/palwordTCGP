package org.fenux.palwordtcgp

import android.app.Application
import org.fenux.palwordtcgp.di.iniKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class Palwordtcgp: Application() {
    override fun onCreate() {
        super.onCreate()
        iniKoin{
            androidLogger()
            androidContext(this@Palwordtcgp)
        }
    }
}