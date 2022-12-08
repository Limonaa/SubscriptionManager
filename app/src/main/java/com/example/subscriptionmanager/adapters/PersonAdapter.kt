package com.example.subscriptionmanager.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.ItemPersonBinding

class PersonAdapter (
    var people: List<Person>
        ) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){

            inner class PersonViewHolder(val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.tvPersonName.text = people[position].name
        holder.binding.tvPersonPrice.text = people[position].price
        holder.binding.tvPersonLastPaid.text = people[position].lastPaid
    }

    override fun getItemCount(): Int {
        return people.size
    }
}