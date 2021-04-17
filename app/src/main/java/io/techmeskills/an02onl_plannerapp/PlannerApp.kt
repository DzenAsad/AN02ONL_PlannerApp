package io.techmeskills.an02onl_plannerapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import io.techmeskills.an02onl_plannerapp.model.DatabaseConstructor
import io.techmeskills.an02onl_plannerapp.model.DB
import io.techmeskills.an02onl_plannerapp.screen.add.AddViewModel
import io.techmeskills.an02onl_plannerapp.screen.login.LoginViewModel
import io.techmeskills.an02onl_plannerapp.screen.main.MainViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PlannerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PlannerApp)
            modules(listOf(viewModels, storageModule, preferencesModule))
        }
    }

    private val viewModels = module {
        viewModel { LoginViewModel(get(), get()) }
        viewModel { MainViewModel(get(), get()) }
        viewModel { AddViewModel(get(), get()) }
    }


    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }
        factory { get<DB>().notesDao() }
    }

    private val preferencesModule = module {
        single { provideSettingsPreferences(androidApplication()) }
    }

    private fun provideSettingsPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences(Companion.PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    companion object {
        private const val PREFERENCES_FILE_KEY = "io.techmeskills.an02onl_plannerapp.settings_preferences"
    }
}