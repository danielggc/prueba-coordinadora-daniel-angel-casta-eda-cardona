import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.shipping.test_cordinadora.adapters.RecyclerAdapterReorderLoad
class ItemTouchHelperCallback (private val adapter: RecyclerAdapterReorderLoad): ItemTouchHelper.Callback() {

    // Métodos necesarios que debes sobrescribir

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition

        adapter.deliveryInfo.intercambiarElementos(fromPosition, toPosition)

        adapter.notifyItemMoved(fromPosition, toPosition)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    fun <T> MutableList<T>.intercambiarElementos(posicionOrigen: Int, posicionDestino: Int) {
        val temp = this[posicionOrigen]
        this[posicionOrigen] = this[posicionDestino]
        this[posicionDestino] = temp
    }

}