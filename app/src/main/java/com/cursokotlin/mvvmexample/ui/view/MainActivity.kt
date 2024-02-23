package com.cursokotlin.mvvmexample.ui.view

import ItemTouchHelperCallback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.adapters.RecyclerAdapterDeliveryInfo
import com.cursokotlin.mvvmexample.data.model.DeliveryInfo
import com.cursokotlin.mvvmexample.databinding.ReorderLoadBinding
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.adapters.RecyclerAdapterReorderLoad

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : RecyclerAdapterDeliveryInfo = RecyclerAdapterDeliveryInfo()
    val mAdapter2 : RecyclerAdapterReorderLoad = RecyclerAdapterReorderLoad()

    private lateinit var binding: ReorderLoadBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReorderLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.quoteModel.observe(this, Observer {
        })

        val moreButton: ImageView = findViewById(R.id.icon_button)
        moreButton.setOnClickListener { view ->
            showMoreActionsMenu(view)
        }
        setUpRecyclerViewR()





    }
    private fun showMoreActionsMenu(button: View) {
        val popupMenu = PopupMenu(this, button)
        menuInflater.inflate(R.menu.options_menu, popupMenu.menu)
        popupMenu.show()
    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.rvDeliveryInfo) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getDeliveryInfo(), this)
        mRecyclerView.adapter = mAdapter
    }

    fun setUpRecyclerViewR(){
        mRecyclerView = findViewById(R.id.rvReorderLoad) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter2.RecyclerAdapter(getDeliveryInfo(), this)
        mRecyclerView.adapter = mAdapter2

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(mAdapter2))
        val recyclerView: RecyclerView = findViewById(R.id.rvReorderLoad)

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    fun getDeliveryInfo(): MutableList<DeliveryInfo> {
        val deliveryInfos: MutableList<DeliveryInfo> = ArrayList()

        val remitente = DeliveryInfo.Remitente(
            ciudad = "MEDELLIN (ANT)",
            nit = "890904713",
            direccion = "BIENCREADO S.A.S",
            telefono = "3235020433",
            nombre = "FLORIDABRAN"

        )

        val destinatario = DeliveryInfo.Destinatario(
            ciudad = "MEDELLIN (ANT)",
            nit = "1088344013",
            direccion = "CRA 52 # 45 EL POBLADO",
            telefono = "3235020433",
            nombre = "AUTOMATIZACION6"
        )

        val fechaHoraRegistro = DeliveryInfo.FechaHoraRegistro(
            seconds = 1708439753,
            nanoseconds = 49000000
        )

        val ubicacion = DeliveryInfo.Ubicacion(
            longitud = "0",
            latitud = "0"
        )

        val estado = DeliveryInfo.Estado(
            nivelServicio = "Servicio Estandar 222",
            abreviadoNivelServicio = "Estandar",
            abreviadoCuenta = "CC",
            codigoTipoCuenta = 1,
            orden = 10000,
            codigoNivelServicio = 1,
            codigoSeguridad = "",
            tienePago = false,
            observaciones = "",
            unidadesAsignadas = listOf("773940715763001"),
            codigoProducto = 1,
            codigoTerminalDestino = 2,
            nombreTerminalDestino = "MEDELLÍN",
            activo = true,
            id = "01wzfYYVtHKKJzfIbRlF"
        )

        val deliveryInfo = DeliveryInfo(
            codigoRemision = "73940715763",
            codigoTerminalOrigen = 2,
            telefonoDestinatario = destinatario.telefono,
            direccionDestinatario = destinatario.direccion,
            ubicacion = ubicacion,
            estado = estado
        )

        deliveryInfos.add(deliveryInfo)
        deliveryInfos.add(deliveryInfo)
        deliveryInfos.add(deliveryInfo)
        deliveryInfos.add(deliveryInfo)


        return deliveryInfos
    }

}