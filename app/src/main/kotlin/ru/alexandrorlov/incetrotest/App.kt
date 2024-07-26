package ru.alexandrorlov.incetrotest

import android.app.Application
import ru.alexandrorlov.incetrotest.detail.di.DaggerDetailComponent
import ru.alexandrorlov.incetrotest.detail.di.DetailComponent
import ru.alexandrorlov.incetrotest.di.AppComponent
import ru.alexandrorlov.incetrotest.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    init {
        Timber.tag("OAE").d("App start")
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        appComponent = DaggerAppComponent.factory().create(this)
        detailComponent = DaggerDetailComponent.factory().create(appComponent)

    }

    companion object {
        private lateinit var appComponent: AppComponent
        lateinit var detailComponent: DetailComponent

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
