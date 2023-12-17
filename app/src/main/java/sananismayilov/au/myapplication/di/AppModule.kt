package sananismayilov.au.myapplication.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sananismayilov.au.myapplication.retrofit.CountryAPI
import sananismayilov.au.myapplication.roomdb.PeopleDB
import sananismayilov.au.myapplication.roomdb.PeopleDao
import sananismayilov.au.myapplication.util.Util
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideCountryApi(): CountryAPI {
        val countryAPI: CountryAPI
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.BASE_URL)
            .build()

        countryAPI = retrofit.create(CountryAPI::class.java)
        return countryAPI
    }

    @Provides
    @Singleton
    fun providePeopleDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        PeopleDB::class.java, "Notedb"
    ).build()

    @Provides
    @Singleton
    fun providePeopleDao(db: PeopleDB) = db.getDao()

}