package com.volkankelleci.again

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.again.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingViewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()?.setTitle("History")

        shoppingViewModel= ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

        //adapter define
        val adapter=HistoryFragmentAdapter(listOf(),shoppingViewModel)
        binding.historyRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.historyRecyclerView.adapter=adapter

        shoppingViewModel.getShopping.observe(viewLifecycleOwner, Observer{
            adapter.shop=it

        })







    }

}