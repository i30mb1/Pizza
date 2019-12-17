package n7.pizza.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import n7.pizza.ui.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Application): ApplicationComponent
    }

    val mainViewModel: MainViewModel
}
