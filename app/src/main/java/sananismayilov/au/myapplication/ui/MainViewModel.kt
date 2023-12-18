package sananismayilov.au.myapplication.ui

import City
import Country
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sananismayilov.au.myapplication.data.PeopleEntity
import sananismayilov.au.myapplication.retrofit.CountryAPI
import sananismayilov.au.myapplication.roomdb.PeopleDao
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val countryAPI: CountryAPI, val peopleDao: PeopleDao) :
    ViewModel() {
    val peoplelist = MutableLiveData<List<PeopleEntity>>()
    val countrylist = MutableLiveData<List<Country>>()
    val citylist = MutableLiveData<List<City>>()

    fun getDatafromApi(context: Context, internetconnection: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            if (internetconnection) {
                val response = countryAPI.getCountry()
                if (response.isSuccessful) {
                    peopleDao.deleteAllPeople()
                    val countrylist = response.body()?.countryList
                    for (i in countrylist!!) {
                        for (j in i.cityList) {
                            for (x in j.peopleList) {
                                val peopleEntity = PeopleEntity(
                                    x.humanId, x.name, x.surname, j.cityId, i.countryId
                                )
                                peopleDao.insertPeople(peopleEntity)
                            }
                        }
                    }
                }
            } else {
                getAllDatafromRoom(context)
            }
        }

    }

    fun getAllDatafromRoom(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            Toast.makeText(context, "GetDatafromRoom", Toast.LENGTH_LONG).show()
            peoplelist.value = peopleDao.getAllPeople()
        }
    }

    fun getCitywithId(country: Country) {
        citylist.value = country.cityList
    }

    fun getCountry(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = countryAPI.getCountry()
            if (response.isSuccessful) {
                countrylist.value = response.body()?.countryList
            }


        }
    }

    fun getCountrywithId(countryid: Int, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            peoplelist.value = peopleDao.getPeoplewithCountryid(countryid)
        }
    }

    fun getPeoplewithcityId(cityid: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            peoplelist.value = peopleDao.getPeoplewithCityId(cityid)
        }
    }
}