package sananismayilov.au.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sananismayilov.au.myapplication.util.Util.BASE_URL

class RetrofitClient {
    companion object {
        private var instance: CountryAPI? = null

        @Synchronized
        fun getInstance(): CountryAPI {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(CountryAPI::class.java)

            return instance!!
        }
    }
}