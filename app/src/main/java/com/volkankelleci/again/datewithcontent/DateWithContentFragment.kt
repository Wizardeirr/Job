package com.volkankelleci.again.datewithcontent

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.again.R
import com.volkankelleci.again.databinding.FragmentDateWithContentBinding
import com.volkankelleci.again.repo.ShoppingListRepository
import com.volkankelleci.again.room.ShoppingListDatabase
import com.volkankelleci.again.viewmodel.ShoppingListViewModel
import com.volkankelleci.again.viewmodel.ShoppingListViewModelFactory


class DateWithContentFragment : Fragment() {
    private var _binding: FragmentDateWithContentBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var adapter: DateWithContentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentDateWithContentBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val database = ShoppingListDatabase.getInstance(requireContext())
        val dao = database.shoppingListDao()
        val repository = ShoppingListRepository(dao)
        viewModel = ViewModelProvider(this, ShoppingListViewModelFactory(repository))
            .get(ShoppingListViewModel::class.java)


        adapter= DateWithContentAdapter(listOf())
        binding.dateWithContentRecycler.layoutManager= LinearLayoutManager(requireContext())
        binding.dateWithContentRecycler.adapter=adapter

        val argDate =arguments?.getString("date")
        (this.context as AppCompatActivity).supportActionBar!!.title = "${argDate}"
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fragmentManager?.popBackStack()

        viewModel.get(argDate!!).observe(viewLifecycleOwner, Observer { shoppingList ->
            val contentListAdapter = DateWithContentAdapter(shoppingList)
            binding.dateWithContentRecycler.adapter = contentListAdapter
        })
        }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    }
