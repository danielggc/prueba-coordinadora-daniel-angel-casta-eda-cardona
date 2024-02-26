package com.shipping.test_cordinadora.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shipping.test_cordinadora.R
import com.shipping.test_cordinadora.domain.model.Remission

class RecyclerAdapterDeliveryInfo : RecyclerView.Adapter<RecyclerAdapterDeliveryInfo.ViewHolder>() {


    var deliveryInfo: MutableList<Remission>  = ArrayList()
    lateinit var context:Context
    fun clear() {
        deliveryInfo.clear()
        notifyDataSetChanged()
    }

    fun add(newDeliveryInfo: Remission) {
        val existingItem = deliveryInfo.find { it.id == newDeliveryInfo.id }
        if (existingItem == null) {
            deliveryInfo.add(newDeliveryInfo)
            notifyItemInserted(deliveryInfo.size - 1)
        }
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
        val codigoRemision                      = view.findViewById(R.id.codigo_remision_item) as TextView
        val direccionDestinatario               = view.findViewById(R.id.direccion_destinatario_item) as TextView
        val nombreTerminalDestino               = view.findViewById(R.id.nombre_terminal_destino_item) as TextView
        var expendableLayout : RelativeLayout   = itemView.findViewById(R.id.expandable_layout)
        var linearLayout                        = view.findViewById<ImageButton>(R.id.icon_button_user)
        var cellPone                            = view.findViewById(R.id.telefono_item_r) as TextView
        var ownerDestination                    = view.findViewById(R.id.destinatario_item_r) as TextView
        var origen                              = view.findViewById(R.id.origen_Info_item_r) as TextView

        fun bind(remissionData:Remission, context: Context){
            codigoRemision.text             = remissionData.codigoRemision
            direccionDestinatario.text      = remissionData.direccionDestinatario
            nombreTerminalDestino.text      = remissionData.firstOrder.toString()
            cellPone.text                   = remissionData.telefonoDestinatario
            ownerDestination.text           = remissionData.nombreDestinatario
            origen.text                     = remissionData.oriogen
        }

    }

}