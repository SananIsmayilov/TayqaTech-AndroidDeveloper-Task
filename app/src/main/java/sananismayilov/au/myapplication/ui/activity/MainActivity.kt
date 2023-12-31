package sananismayilov.au.myapplication.ui.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import sananismayilov.au.myapplication.adapter.CityAdapter
import sananismayilov.au.myapplication.adapter.CountryAdapter
import sananismayilov.au.myapplication.adapter.PeopleAdapter
import sananismayilov.au.myapplication.databinding.ActivityMainBinding
import sananismayilov.au.myapplication.ui.viewmodel.MainViewModel
import sananismayilov.au.myapplication.util.Util.checkInternet

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var filterclick = false
    private var sortclick = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sharedPreferences = this.getSharedPreferences("PeoplePref", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        mainBinding.swipelayout.setOnRefreshListener {
            val internetconnection = checkInternet(this)
            mainViewModel.getDatafromApi(this, internetconnection)
            mainViewModel.getAllDatafromRoom(this)
            Handler().postDelayed(Runnable {
                mainBinding.swipelayout.isRefreshing = false
            }, 2000)
        }

        mainBinding.filterbutton.setOnClickListener {
            showFilter()
        }
        mainBinding.sortbutton.setOnClickListener {
            showSort()
        }

        observePeople()
    }

    fun observePeople() {
        mainViewModel.peoplelist.observe(this, Observer {
            mainBinding.peoplerecyclerview.layoutManager = LinearLayoutManager(this)
            val peopleadapter = PeopleAdapter(this, it)
            mainBinding.peoplerecyclerview.adapter = peopleadapter
            mainBinding.filterrecyclerview.visibility = View.INVISIBLE
            mainBinding.sortrecyclerview.visibility = View.INVISIBLE
            filterclick = false
            sortclick = false
        })

        mainViewModel.countrylist.observe(this, Observer {
            mainBinding.filterrecyclerview.layoutManager = LinearLayoutManager(this)
            val countryAdapter = CountryAdapter(this, it, mainViewModel)
            mainBinding.filterrecyclerview.adapter = countryAdapter
        })

        mainViewModel.citylist.observe(this, Observer {
            mainBinding.sortrecyclerview.layoutManager = LinearLayoutManager(this)
            val cityAdapter = CityAdapter(this, it, mainViewModel)
            mainBinding.sortrecyclerview.adapter = cityAdapter

        })
    }

    fun showFilter() {
        val internetconnection = checkInternet(this)
        if (!filterclick) {
            mainViewModel.getCountry(this,internetconnection)
            mainBinding.filterrecyclerview.visibility = View.VISIBLE
            mainBinding.sortrecyclerview.visibility = View.INVISIBLE
            filterclick = true
        } else {
            mainBinding.filterrecyclerview.visibility = View.INVISIBLE
            filterclick = false
        }
    }

    fun showSort() {
        if (!sortclick) {
            mainBinding.sortrecyclerview.visibility = View.VISIBLE
            mainBinding.filterrecyclerview.visibility = View.INVISIBLE
            sortclick = true
        } else {
            mainBinding.sortrecyclerview.visibility = View.INVISIBLE
            sortclick = false
        }
    }

    override fun onResume() {
        val first = sharedPreferences.getBoolean("firstopen", false)
        if (!first) {
            val internetconnection = checkInternet(this)
            mainViewModel.getDatafromApi(applicationContext, internetconnection)
            editor.putBoolean("firstopen", true)
            editor.commit()
        }
        mainViewModel.getAllDatafromRoom(this)
        super.onResume()
    }
}