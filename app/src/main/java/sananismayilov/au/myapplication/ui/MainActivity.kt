package sananismayilov.au.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sananismayilov.au.myapplication.R
import sananismayilov.au.myapplication.adapter.PeopleAdapter
import sananismayilov.au.myapplication.databinding.ActivityMainBinding
import sananismayilov.au.myapplication.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getDatafromApi(applicationContext)

        mainViewModel.getAllDatafromRoom(applicationContext)

        observe()
    }

    fun observe(){
        mainViewModel.peoplelist.observe(this, Observer {
            mainBinding.peoplerecyclerview.layoutManager = LinearLayoutManager(this)
            val peopleadapter = PeopleAdapter(this,it)
            mainBinding.peoplerecyclerview.adapter = peopleadapter
        })
    }
}