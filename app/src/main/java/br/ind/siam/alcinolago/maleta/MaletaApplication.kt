package br.ind.siam.alcinolago.maleta

import android.app.Application
import android.widget.Toast
import br.ind.siam.alcinolago.maleta.di.appInject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MaletaApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        startKoin {
            androidContext(this@MaletaApplication)
            modules(appInject)
        }

        if (BuildConfig.DEBUG) Toast.makeText(this, "App em modo de debug", Toast.LENGTH_LONG)
            .show()
    }
}