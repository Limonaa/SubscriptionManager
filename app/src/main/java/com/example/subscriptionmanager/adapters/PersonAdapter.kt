package com.example.subscriptionmanager.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.subscriptionmanager.data.Person
import com.example.subscriptionmanager.databinding.ItemPersonBinding
import com.example.subscriptionmanager.viewmodels.MainViewModel

class PersonAdapter (
    private var peopleList: List<Person>
        ) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {

        return PersonViewHolder(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        holder.binding.apply {
            tvPersonName.text = peopleList[position].name
            tvPersonPrice.text = "${peopleList[position].price} PLN"
        }

        holder.binding.root.setOnClickListener {
            onItemClickListener?.let {
                it(peopleList[position])
            }
        }

        holder.binding.fabRemovePerson.setOnClickListener {

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