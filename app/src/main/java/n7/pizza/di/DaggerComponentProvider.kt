package n7.pizza.di

import android.app.Activity
import androidx.fragment.app.Fragment
import n7.pizza.MyApplication

interface DaggerComponentProvider {

    val component: ApplicationComponent
}

val Activity.injector get() = (application as MyApplication).component
val Fragment.injector get() = (requireActivity().application as MyApplication).component
