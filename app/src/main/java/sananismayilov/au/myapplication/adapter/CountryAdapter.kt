package sananismayilov.au.myapplication.adapter

import Country
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sananismayilov.au.myapplication.databinding.CountryrowBinding
import sananismayilov.au.myapplication.ui.MainViewModel

class CountryAdapter(val context: Context, val countrylist: List<Country>,val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    inner class CountryHolder(val binding: CountryrowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = CountryrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryHolder(view)
    }

    override fun getItemCount(): Int {
        return countrylist.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val country = countrylist[position]
        holder.binding.countryname.text = country.name
        holder.binding.countrylinear.setOnClickListener {
            mainViewModel.getCountrytosendid(country.countryId,context)
            holder.binding.checkbox.isChecked = true
        }
    }

}