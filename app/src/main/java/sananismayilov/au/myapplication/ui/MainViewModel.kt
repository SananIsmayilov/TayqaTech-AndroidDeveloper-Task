package sananismayilov.au.myapplication.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sananismayilov.au.myapplication.data.PeopleEntity
import sananismayilov.au.myapplication.retrofit.RetrofitClient
import sananismayilov.au.myapplication.roomdb.PeopleDB

class MainViewModel : ViewModel() {
    val peoplelist = MutableLiveData<List<PeopleEntity>>()

    fun getDatafromApi(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val peopledao = PeopleDB(context).getDao()
            val response = RetrofitClient.getInstance().getCountry()
            if (response.isSuccessful) {
                peopledao.deleteAllPeople()
                val countrylist = response.body()?.countryList
                if (countrylist != null) {
                    for (i in countrylist) {
                        for (j in i.cityList) {
                            for (x in j.peopleList) {
                                val peopleEntity = PeopleEntity(
                                    x.humanId, x.name, x.surname, j.cityId, i.countryId
                                )
                                peopledao.insertPeople(peopleEntity)
                            }
                        }
                    }
                }
            }
        }

    }

    fun getAllDatafromRoom(context: Context){
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context,"GetDatafromRoom",Toast.LENGTH_LONG).show()
            val peopledao = PeopleDB(context).getDao()
            peoplelist.value = peopledao.getAllPeople()
        }
    }





}