package sananismayilov.au.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sananismayilov.au.myapplication.R
import sananismayilov.au.myapplication.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        val response = RetrofitClient.getInstance().getCountry()
                        if (response.isSuccessful) {
                            val countryList = response.body()

                            for (i in countryList?.countryList!!){
                                println("----")
                                println("${i.name} : " )
                                for (j in i.cityList){
                                    println("${j.name} : " )
                                    for (x in j.peopleList){
                                        println("${x.name} : " )
                                        println("${x.surname} : " )
                                    }
                                }
                            }

                        } else {
                            // Hata durumunu işleyin
                            println("ERROR unsuccessful: ${response.code()}")
                        }
                    } catch (e: Exception) {
                        // Hata durumunu işleyin
                        println("ERROR: ${e.printStackTrace()}")
                    }
                }
    }
}