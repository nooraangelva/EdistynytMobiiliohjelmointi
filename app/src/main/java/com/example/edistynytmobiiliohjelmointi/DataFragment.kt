package com.example.edistynytmobiiliohjelmointi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edistynytmobiiliohjelmointi.databinding.DataFragmentBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.data_fragment.*

class DataFragment : Fragment() {

    companion object {
        fun newInstance() = DataFragment()
    }

    private var _binding: DataFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: RecyclerAdapter

    private lateinit var linearLayoutManager: LinearLayoutManager

    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    private lateinit var viewModel: DataViewModel
    private lateinit var viewModelFactory: DataViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataFragmentBinding.inflate(inflater, container, false)

        val root: View = binding.root

        linearLayoutManager = LinearLayoutManager(context)
        binding.dataRecyclerView.layoutManager = linearLayoutManager

        viewModelFactory = DataViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[DataViewModel::class.java]
        _binding.viewModel = viewModel

        // Observers
        viewModel.adapter.observe(viewLifecycleOwner, Observer { adapter ->

            binding.dataRecyclerView.adapter = adapter

        })

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}