package com.cursokotlin.mvvmexample.ui.view
import ItemTouchHelperCallback
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.adapters.RecyclerAdapterReorderLoad
import com.cursokotlin.mvvmexample.databinding.ReorderLoadBinding
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController


import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlin.mvvmexample.domain.model.Remission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlankFragment3 : Fragment() {
    private var _binding: ReorderLoadBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel: QuoteViewModel by viewModels()
    val mAdapter : RecyclerAdapterReorderLoad = RecyclerAdapterReorderLoad()
    lateinit var mRecyclerView : RecyclerView
    private var currentPage = 0
    private  var hasExecuted:Boolean = false
    private lateinit var sharedPreferences: SharedPreferences
    var isloadin:Boolean =false
    var  shouldUpdate: Boolean = false

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
            Log.d("onResumeFragment", "onScrolled: ")
            initDataIntoLIst()
            sharedPreferences.edit().putBoolean("has_executed", true).apply()
            hasExecuted = true


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible  = it
            if(!it){
                Log.d("TAG", "onViewCreated: is posible o r not  ")
                mAdapter.clear()
                initDataIntoLIst()

            }
        })
        setUpRecyclerView()
        initScrollRecycler()

        val saveButton: ImageView = binding.root.findViewById (R.id.icon_button_save_list)
        saveButton.setOnClickListener {
            currentPage = 0
            quoteViewModel.updateRemissionList(mAdapter.getDeliveryInfoList())
            quoteViewModel.isLoading.postValue(true)

        }


        val getBackButton: ImageView = binding.root.findViewById (R.id.icon_get_back)
        getBackButton.setOnClickListener {
            currentPage = 0
            findNavController().navigate(R.id.action_blankFragment3_to_blankFragment2)

        }

    }



    fun setUpRecyclerView(){
        mRecyclerView =  binding.root.findViewById (R.id.rvReorderLoad) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter.RecyclerAdapter( mutableListOf() , requireContext() )
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
                val totalItemCount = mAdapter.itemCount
                Log.d("userTAGRELOAD", "onScrolled: [$totalItemCount] [$lastVisibleItemPosition]")
                if (totalItemCount -1 == lastVisibleItemPosition  && !isloadin  ) {
                    funtionScroll()
                }

            }
        })
    }

    fun funtionScroll(){
        isloadin = true;
        quoteViewModel.getRemissionsInBatches(currentPage + 5, currentPage)
            .observe(viewLifecycleOwner) { remisions ->
                remisions.map { mAdapter.add(it) }
                isloadin = false
            }
        currentPage += 5
    }

    fun initDataIntoLIst(){
        Log.d("INITFragment", "onScrolled: ")
        val dd = quoteViewModel.getRemissionsInBatches( 5, currentPage)
        dd.observe(viewLifecycleOwner) { remisions ->
            Log.d("GETDATAVIEW", "onScrolled: " + remisions)
            remisions.map { mAdapter.add(it) }
        }
        currentPage = 5
    }
}
