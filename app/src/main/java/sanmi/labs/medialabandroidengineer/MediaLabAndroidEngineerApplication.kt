package sanmi.labs.medialabandroidengineer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sanmi.labs.medialabandroidengineer.di.appModule

class MediaLabAndroidEngineerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MediaLabAndroidEngineerApplication)
            modules(appModule)
        }
    }
}