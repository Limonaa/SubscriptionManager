package com.example.subscriptionmanager.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.subscriptionmanager.data.Subscription
import com.example.subscriptionmanager.databinding.ItemSubscriptionBinding

class SubscriptionAdapter (
    private var subscriptions: List<Subscription>
        ) : RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>(){

    inner class  SubscriptionViewHolder(val binding: ItemSubscriptionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {

        return SubscriptionViewHolder(ItemSubscriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {

        holder.binding.apply {
            tvSubName.text = subscriptions[position].name
            tvSubPayment.text = subscriptions[position].paymentDate
            tvSubPrice.text = "${subscriptions[position].price}PLN"
            ivImage.setImageBitmap(subscriptions[position].image)
            tvImage.text = subscriptions[position].textImage
            subscriptions[position].backgroundColor?.let { vImage.setBackgroundColor(it) }
        }

        holder.binding.root.setOnClickListener {
            onItemClickListener?.let {
                it(subscriptions[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return subscriptions.size
    }

    private var onItemClickListener : ((Subscription) -> Unit)? = null

    fun setOnItemClickListener(listener: (Subscription) -> Unit) {
        onItemClickListener = listener
    }
}