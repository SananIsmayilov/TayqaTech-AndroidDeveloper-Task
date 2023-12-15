package sananismayilov.au.myapplication.retrofit



import CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryAPI {
    @GET("tayqa/tiger/api/development/test/TayqaTech/getdata")
    suspend fun getCountry() : Response<CountryResponse>

}