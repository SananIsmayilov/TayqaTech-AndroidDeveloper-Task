package sananismayilov.au.myapplication.ui

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
import sananismayilov.au.myapplication.roomdb.PeopleDB
import sananismayilov.au.myapplication.roomdb.PeopleDao
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val countryAPI: CountryAPI,val peopleDao: PeopleDao) : ViewModel() {
    val peoplelist = MutableLiveData<List<PeopleEntity>>()
    val countrylist = MutableLiveData<List<Country>>()

    fun getDatafromApi(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
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
        }

    }

    fun getAllDatafromRoom(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            Toast.makeText(context, "GetDatafromRoom", Toast.LENGTH_LONG).show()
            peoplelist.value = peopleDao.getAllPeople()
        }
    }

    fun getsendidDatafromRoom(city_id: Int, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            peoplelist.value = peopleDao.getPeopleByCity(city_id)
        }
    }

    fun getCountry(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = countryAPI.getCountry()
            if (response.isSuccessful) {
                countrylist.value = response.body()?.countryList
            }


        }
    }

    fun getCountrytosendid(countryid: Int, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            peoplelist.value = peopleDao.getPeopleByCountry(countryid)
        }
    }
}