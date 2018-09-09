package app.com.notesappwithandroidarchcomponents

import android.app.Activity
import app.com.notesappwithandroidarchcomponents.helpers.LogDebugTree
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class NotesApplication : MultidexApplication(), HasActivityInjector {
    companion object {
        lateinit var component: AppComponent
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector


    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        component.inject(this)
        initLogging()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            val formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(true)
                    .methodOffset(5)
                    .tag("Notes")
                    .build()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
            Timber.plant(LogDebugTree())
        }
    }
}
