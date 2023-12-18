package sananismayilov.au.myapplication.adapter

import City
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import sananismayilov.au.myapplication.databinding.CitylayoutBinding

class CityAdapter(val context: Context,val citylist : List<City>) :  RecyclerView.Adapter<CityAdapter.CityHolder>(){
    inner class CityHolder(val binding : CitylayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
      val view = CitylayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityHolder(view)
    }

    override fun getItemCount(): Int {
      return citylist.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.binding.cityname.text = citylist[position].name
    }
}