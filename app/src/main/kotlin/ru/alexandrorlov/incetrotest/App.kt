package ru.alexandrorlov.incetrotest

import android.app.Application
import ru.alexandrorlov.incetrotest.di.AppComponent
import ru.alexandrorlov.incetrotest.di.DaggerAppComponent
import ru.alexandrorlov.incetrotest.feature.di.DaggerFeatureComponent
import ru.alexandrorlov.incetrotest.feature.di.FeatureComponent
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        appComponent = DaggerAppComponent.factory().create(this)
        featureComponent = DaggerFeatureComponent.factory().create(
            mainDependencies = appComponent,
            detailDependencies = appComponent,
            commonDependencies = appComponent,
        )
    }

    companion object {
        private lateinit var appComponent: AppComponent
        lateinit var featureComponent: FeatureComponent

    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(
                object : Timber.DebugTree() {
                    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                        super.log(priority, "----", "$tag --> $message", t)
                    }
                    override fun createStackElementTag(element: StackTraceElement) = StringBuilder().apply {
                        append(element.lineNumber)
                        append(" ")
                        append(super.createStackElementTag(element))
                        append(" ")
                        append(element.methodName)
                    }.toString()
                },
            )
        }
    }
}
