package com.cursokotlin.mvvmexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.domain.model.Remission

class RecyclerAdapterDeliveryInfo : RecyclerView.Adapter<RecyclerAdapterDeliveryInfo.ViewHolder>() {


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
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = deliveryInfo.get(position)
        holder.bind(item, context)

        val isExpandable: Boolean = deliveryInfo[position].StatusExpandable

        holder.expendableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener{
            val version = deliveryInfo[position]
            version.StatusExpandable = !item.StatusExpandable
            notifyItemChanged(position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)



        return ViewHolder(layoutInflater.inflate(R.layout.textview_layout, parent, false))
    }




    override fun getItemCount(): Int {
        return deliveryInfo.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
        val realName = view.findViewById(R.id.tvRealName) as TextView
        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        var expendableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)
        var linearLayout  = view.findViewById<ImageButton>(R.id.icon_button_user)
        fun bind(superhero:Remission, context: Context){
            superheroName.text = superhero.codigoRemision
            realName.text = superhero.direccionDestinatario
            publisher.text = superhero.nombreTerminalDestino
        }

    }

}