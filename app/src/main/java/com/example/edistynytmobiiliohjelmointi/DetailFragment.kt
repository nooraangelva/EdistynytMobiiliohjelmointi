package com.example.edistynytmobiiliohjelmointi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edistynytmobiiliohjelmointi.databinding.DetailFragmentBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.data_fragment.*

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // get fragment parameters from previous fragment
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var linearLayoutManager: LinearLayoutManager

    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        linearLayoutManager = LinearLayoutManager(context)
        dataRecyclerView.layoutManager = linearLayoutManager

        viewModelFactory = DetailViewModelFactory(requireContext(), args.userId)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}