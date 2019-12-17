package n7.pizza

import android.app.Application
import android.os.Handler
import android.os.StrictMode
import n7.pizza.di.ApplicationComponent
import n7.pizza.di.DaggerApplicationComponent
import n7.pizza.di.DaggerComponentProvider

class MyApplication : Application(), DaggerComponentProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        enableStrictMode()
        super.onCreate()
    }

    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            Handler().postAtFrontOfQueue {
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectDiskWrites().detectNetwork().detectCustomSlowCalls().detectResourceMismatches().penaltyLog().build())
                StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build())
            }
        }
    }
}
