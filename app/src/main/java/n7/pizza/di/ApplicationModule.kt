package n7.pizza.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ApplicationModule {

    @Reusable
    @Provides
    fun provideDatabase(): FirebaseDatabase {
        return Firebase.database
    }

    @Reusable
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Reusable
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
