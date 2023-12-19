package sananismayilov.au.myapplication.adapter

import Country
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sananismayilov.au.myapplication.databinding.CountrylayoutBinding
import sananismayilov.au.myapplication.ui.viewmodel.MainViewModel

class CountryAdapter(val context: Context, val countrylist: List<Country>,val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    inner class CountryHolder(val binding: CountrylayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = CountrylayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryHolder(view)
    }

    override fun getItemCount(): Int {
        return countrylist.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val country = countrylist[position]
        holder.binding.countryname.text = country.name
        holder.binding.countrylinear.setOnClickListener {
            mainViewModel.getCountrywithId(country.countryId,context,)
            mainViewModel.getCitywithId(country)
            holder.binding.checkbox.isChecked = true
        }
    }

}