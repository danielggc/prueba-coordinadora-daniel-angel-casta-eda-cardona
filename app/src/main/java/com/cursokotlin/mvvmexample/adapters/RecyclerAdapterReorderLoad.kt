package com.cursokotlin.mvvmexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.domain.model.Remission

class RecyclerAdapterReorderLoad : RecyclerView.Adapter<RecyclerAdapterReorderLoad.ViewHolder>() {


    var deliveryInfo: MutableList<Remission>  = ArrayList()
    lateinit var context:Context
    fun clear() {
        deliveryInfo.clear()
        notifyDataSetChanged()
    }

    fun add(newDeliveryInfo: Remission) {
        deliveryInfo.add(newDeliveryInfo)
        notifyItemInserted(deliveryInfo.size - 1)
    }
    fun RecyclerAdapter(superheros : MutableList<Remission>, context: Context){
        this.deliveryInfo = superheros
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = deliveryInfo.get(position)
        holder.bind(item, context, position)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)



        return ViewHolder(layoutInflater.inflate(R.layout.reorder_load_item, parent, false))
    }




    override fun getItemCount(): Int {
        return deliveryInfo.size
    }







    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.findViewById(R.id.CodeRemisionReorder) as TextView
        val realName = view.findViewById(R.id.origenReorder) as TextView
        val status = view.findViewById(R.id.StateReorder) as TextView
        var idex = view.findViewById(R.id.numberTextView) as TextView


        fun bind(deliveryInfo: Remission, context: Context, position: Int){
            superheroName.text       = deliveryInfo.codigoRemision
            realName.text            = deliveryInfo.direccionDestinatario
            status.text              = deliveryInfo.nombreTerminalDestino
            idex.text                = deliveryInfo .firstOrder.toString()
        }

    }

}