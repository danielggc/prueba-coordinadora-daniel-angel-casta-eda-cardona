package com.cursokotlin.mvvmexample.ui.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.adapters.RecyclerAdapterDeliveryInfo
import com.cursokotlin.mvvmexample.databinding.HomeBinding
import com.cursokotlin.mvvmexample.domain.model.Remission
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel: QuoteViewModel by viewModels()
    val mAdapter : RecyclerAdapterDeliveryInfo = RecyclerAdapterDeliveryInfo()
    lateinit var mRecyclerView : RecyclerView
    private var currentPage = 1
    private val pageSize = 10
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeBinding.inflate(inflater, container, false)
        val moreButton: ImageView = binding.root.findViewById (R.id.icon_button)
        moreButton.setOnClickListener { view ->
            showMoreActionsMenu(view)
        }
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel.onCreate()
        setUpRecyclerView()
        quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible  = it
            if(it == false ){
                Log.d("userTAG", "onScrolled: ")
                val dd = quoteViewModel.getRemissionsInBatches(currentPage, pageSize)
                dd.observe( viewLifecycleOwner ) {
                        remisions ->
                    Log.d("userTAG", "onScrolled: " +remisions)
                    remisions.map { mAdapter.add(it) }

                }
                currentPage++

            }
        })




        mRecyclerView.addOnScrollListener( object  : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                Log.d("userTAG", "onScrolled: " + totalItemCount.toString() +"  " + lastVisibleItemPosition.toString())

                if( totalItemCount <= lastVisibleItemPosition+5 ){
                    Log.d("userTAG", "onScrolled: ")

                    val dd = quoteViewModel.getRemissionsInBatches(currentPage * pageSize, pageSize)

                    dd.observe( viewLifecycleOwner ) {
                            remisions ->
                        Log.d("userTAG", "onScrolled: " +remisions)
                        remisions.map { mAdapter.add(it) }

                    }
                    currentPage++

                }
            }

        })

    }

    fun setUpRecyclerView(){
        mRecyclerView =  binding.root.findViewById(R.id.rvDeliveryInfo) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext() )
        val remissionList: MutableList<Remission> = mutableListOf(

            )
        mAdapter.RecyclerAdapter( remissionList , requireContext() )

        mRecyclerView.adapter = mAdapter
    }

    private fun showMoreActionsMenu(button: View) {
        val popupMenu = PopupMenu(requireContext() , button)
        requireActivity().menuInflater.inflate(R.menu.options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_recarge_home -> {
                    mAdapter.clear()
                    quoteViewModel.onCreate()
                    true
                }
                R.id.home_reorder_load_home-> {
                    findNavController().navigate(R.id.action_blankFragment2_to_blankFragment3)
                    true
                }
                R.id.menu_sing_out_home-> {
                    mAdapter.clear()
                    findNavController().navigate(R.id.action_blankFragment2_to_blankFragment22)

                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }



}