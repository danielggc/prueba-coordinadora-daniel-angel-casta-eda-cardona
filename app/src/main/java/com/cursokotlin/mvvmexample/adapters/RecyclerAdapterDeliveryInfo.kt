package com.cursokotlin.mvvmexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.data.model.DeliveryInfo
import com.cursokotlin.mvvmexample.R
class RecyclerAdapterDeliveryInfo : RecyclerView.Adapter<RecyclerAdapterDeliveryInfo.ViewHolder>() {


    var deliveryInfo: MutableList<DeliveryInfo>  = ArrayList()
    lateinit var context:Context


    fun RecyclerAdapter(superheros : MutableList<DeliveryInfo>, context: Context){
        this.deliveryInfo = superheros
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = deliveryInfo.get(position)
        holder.bind(item, context)

        val isExpandable: Boolean = deliveryInfo[position].expandable

        holder.expendableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener{
            val version = deliveryInfo[position]
            version.expandable = !item.expandable
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
        fun bind(superhero:DeliveryInfo, context: Context){
            superheroName.text = superhero.codigoRemision
            realName.text = superhero.direccionDestinatario
            publisher.text = superhero.estado.nombreTerminalDestino
        }

    }

}