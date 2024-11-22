package com.example.adminshriparvatioilandproduct.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.recyclerview.widget.RecyclerView
import com.example.adminshriparvatioilandproduct.databinding.PendingOrdersItemsBinding

class PendingOrderAdapter(private val customerNames:ArrayList<String>,private val quantity:ArrayList<String>,private val productImage:ArrayList<Int>,private val context:Context): RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val binding=PendingOrdersItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PendingOrderViewHolder(binding)
    }




    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
       holder.bind(position)
    }

    override fun getItemCount(): Int =customerNames.size

    inner class PendingOrderViewHolder(private val binding:PendingOrdersItemsBinding):RecyclerView.ViewHolder(binding.root) {
        private var isAccepted=false
        fun bind(position: Int) {
            binding.apply {
                customerName.text=customerNames[position]
                pendingOrderQuantity.text=quantity[position]
                orderProductImage.setImageResource(productImage[position])

                orderAcceptButton.apply {
                    if(!isAccepted){
                        text="Accept"

                    }else{
                        text="Dispatch"
                    }
                    setOnClickListener {

                        if(!isAccepted){
                            text="Dispatch"

                            isAccepted=true
                            showToast("Order is Accepted")



                        }else{

                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order is dispatched")

                        }

                    }
                }


            }
        }
        private fun showToast(message: String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }

    }
}