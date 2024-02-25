package com.cursokotlin.mvvmexample.ui.view

import ItemTouchHelperCallback
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.adapters.RecyclerAdapterReorderLoad
import com.cursokotlin.mvvmexample.databinding.HomeBinding
import com.cursokotlin.mvvmexample.databinding.ReorderLoadBinding
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel
import androidx.lifecycle.Observer


import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlin.mvvmexample.domain.model.Remission
import com.cursokotlin.mvvmexample.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlankFragment3 : Fragment() {
    private var _binding: ReorderLoadBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel: QuoteViewModel by viewModels()
    val mAdapter : RecyclerAdapterReorderLoad = RecyclerAdapterReorderLoad()
    lateinit var mRecyclerView : RecyclerView
    private var currentPage = 1
    private val pageSize = 10
    private val sharedViewModel: SharedViewModel by viewModels()
    private  var hasExecuted:Boolean = false
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("your_shared_prefs", Context.MODE_PRIVATE)
        hasExecuted = sharedPreferences.getBoolean("has_executed", false)

        // Limpiar preferencias compartidas cuando la aplicación se reinicia
        if (savedInstanceState == null) {
            sharedPreferences.edit().putBoolean("has_executed", false).apply()
            hasExecuted = false
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ReorderLoadBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onResume() {
        super.onResume()
        if( !hasExecuted ){
            Log.d("INITHOME", "onScrolled: ")
            setUpRecyclerViewR()
            initDataIntoLIst()
            initScrollRecycler()
            sharedPreferences.edit().putBoolean("has_executed", true).apply()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer { binding.loading.isVisible  = it })

    }



    fun setUpRecyclerViewR(){
        mRecyclerView =  binding.root.findViewById (R.id.rvReorderLoad) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val remissionList: MutableList<Remission> = mutableListOf(
        )
        mAdapter.RecyclerAdapter( remissionList , requireContext() )
        mRecyclerView.adapter = mAdapter
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(mAdapter))
        val recyclerView: RecyclerView = binding.root.findViewById(R.id.rvReorderLoad)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    fun initScrollRecycler(){

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

    fun initDataIntoLIst(){
        quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible  = it
            if(it == false ){
                Log.d("INITHOME", "onScrolled: ")
                val dd = quoteViewModel.getRemissionsInBatches(currentPage, pageSize)
                dd.observe( viewLifecycleOwner ) {
                        remisions ->
                    Log.d("ADDMOREDATA", "onScrolled: " +remisions)
                    remisions.map { mAdapter.add(it) }

                }
                hasExecuted = true
                currentPage++

            }
        })
    }
}