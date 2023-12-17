package sananismayilov.au.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sananismayilov.au.myapplication.data.PeopleEntity
import sananismayilov.au.myapplication.databinding.PeoplerowBinding

class PeopleAdapter(val context : Context,val peoplelist : List<PeopleEntity>) : RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {
    inner class PeopleHolder(val peoplerawbinding : PeoplerowBinding) : RecyclerView.ViewHolder(peoplerawbinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val view = PeoplerowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PeopleHolder(view)
    }

    override fun getItemCount(): Int {
        return peoplelist.size
    }

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        holder.peoplerawbinding.peopletextview.text = peoplelist[position].name
    }
}