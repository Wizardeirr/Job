package com.volkankelleci.again.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.again.databinding.FragmentHistoryListBinding
import com.volkankelleci.again.repo.ShoppingListRepository
import com.volkankelleci.again.room.ShoppingListDatabase
import com.volkankelleci.again.viewmodel.ShoppingListViewModel
import com.volkankelleci.again.viewmodel.ShoppingListViewModelFactory


class HistoryListFragment : Fragment() {
    private var _binding: FragmentHistoryListBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var adapter: HistoryListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentHistoryListBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (this.context as AppCompatActivity).supportActionBar!!.title = "History"


        val database = ShoppingListDatabase.getInstance(requireContext())
        val dao = database.shoppingListDao()
        val repository = ShoppingListRepository(dao)
        viewModel = ViewModelProvider(this, ShoppingListViewModelFactory(repository))
            .get(ShoppingListViewModel::class.java)

        adapter = HistoryListAdapter(emptyList())
        binding.dateRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.dateRecyclerView.adapter=adapter
            viewModel.shoppingList.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.setData(it)
                }
            })
        }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }
