package com.abdosharaf.marsrealestate.listScreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abdosharaf.marsrealestate.R
import com.abdosharaf.marsrealestate.databinding.FragmentListBinding
import com.abdosharaf.marsrealestate.utils.Constants.Companion.MarsApiFilter

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private val adapter = MarsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.rvList.adapter = adapter
        adapter.onItemClicked = { item ->
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToDetailsFragment(
                    item
                )
            )
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getProperties(
            when (item.itemId) {
                R.id.miRent -> MarsApiFilter.SHOW_RENT
                R.id.miSale -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }
}