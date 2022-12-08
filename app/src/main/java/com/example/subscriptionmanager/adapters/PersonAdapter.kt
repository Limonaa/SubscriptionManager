package com.example.subscriptionmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.ItemPersonBinding

class PersonAdapter (
    var peopleList: List<Person>
        ) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){

    inner class PersonViewHolder(val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        holder.binding.tvPersonName.text = peopleList[position].name
        holder.binding.tvPersonPrice.text = peopleList[position].price
        holder.binding.tvPersonLastPaid.text = peopleList[position].lastPaid

        holder.binding.root.setOnClickListener {
            onItemClickListener?.let {
                it(peopleList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    private var onItemClickListener : ((Person) -> Unit)? = null

    fun setOnItemClickListener(listener: (Person) -> Unit) {
        onItemClickListener = listener
    }
}