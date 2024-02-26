package com.shipping.test_cordinadora.ui.view


import android.content.SharedPreferences
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
import com.shipping.test_cordinadora.R
import com.shipping.test_cordinadora.adapters.RecyclerAdapterDeliveryInfo
import com.shipping.test_cordinadora.databinding.HomeBinding
import com.shipping.test_cordinadora.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context

@AndroidEntryPoint
class Home : Fragment() {
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!
    private val quoteViewModel: QuoteViewModel by viewModels()
    val mAdapter : RecyclerAdapterDeliveryInfo = RecyclerAdapterDeliveryInfo()
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

    
    }
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


    override fun onResume() {
        super.onResume()
        if( !hasExecuted ){
            Log.d("onResume", "onScrolled: ")
            mAdapter.clear()
            quoteViewModel.onCreate()
            initDataIntoLIst()
            sharedPreferences.edit().putBoolean("has_executed", true).apply()
            hasExecuted = true

        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if( !it ) {
                mAdapter.clear()
                currentPage = 0
                initDataIntoLIst()
            }
            binding.loading.isVisible  = it
        })
        setUpRecyclerView()
        initScrollRecycler()
    }

    fun setUpRecyclerView(){
        mRecyclerView =  binding.root.findViewById(R.id.rvDeliveryInfo) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext() )
        mAdapter.RecyclerAdapter( mutableListOf() , requireContext() )

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
                    quoteViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                        if (!it) {
                            findNavController().navigate(R.id.action_blankFragment2_to_blankFragment3)
                        }
                    })
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