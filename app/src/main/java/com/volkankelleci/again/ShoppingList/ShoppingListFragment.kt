package com.volkankelleci.again.ShoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.again.databinding.FragmentShoppingListBinding
import com.volkankelleci.again.history.HistoryListFragment
import com.volkankelleci.again.repo.ShoppingListRepository
import com.volkankelleci.again.room.ShoppingListDatabase
import com.volkankelleci.again.room.ShoppingListItem
import com.volkankelleci.again.viewmodel.ShoppingListViewModel
import com.volkankelleci.again.viewmodel.ShoppingListViewModelFactory
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ShoppingListFragment : Fragment() {
    private var _binding: FragmentShoppingListBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var adapter: ShoppingListAdapter
    private lateinit var shoplists: List<ShoppingListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding=FragmentShoppingListBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "Please Fill to List", Toast.LENGTH_SHORT).show()

        val database = ShoppingListDatabase.getInstance(requireContext())
        val dao = database.shoppingListDao()
        val repository = ShoppingListRepository(dao)
        viewModel = ViewModelProvider(this,
            ShoppingListViewModelFactory(repository)).
            get(ShoppingListViewModel::class.java)

        adapter= ShoppingListAdapter(emptyList(),viewModel)
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
        viewModel.shoppingList.observe(viewLifecycleOwner, Observer {items ->
            items?.let {
                adapter.items=it
                adapter.notifyDataSetChanged()
            }
        })
        binding.addButton.setOnClickListener {
            val date= ZonedDateTime.now()
            val takeDate= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(date)
            val name = binding.editTextName.text.toString()
            val item = ShoppingListItem(0,name,0,takeDate)
            if (item!=null){
                binding.deleteList.visibility=View.VISIBLE
                binding.deleteList.setOnClickListener {
                    binding.deleteList.visibility=View.INVISIBLE

                    viewModel.clearList()
                }
            }
            val bundle = Bundle()
            bundle.putString("selectedDate", takeDate)
            val fragment = HistoryListFragment()
            viewModel.addItem(item)

            fragment.arguments = bundle
            binding.editTextName.text.clear()
            adapter.notifyDataSetChanged()
        }
        binding.completeButton.setOnClickListener {

            adapter.notifyDataSetChanged()
            viewModel.shoppingList.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.items=it
                    adapter.notifyDataSetChanged()
                    shoplists=it
                }
            })
            val action=ShoppingListFragmentDirections.actionBlankFragmentToBlankFragment2()
            findNavController().navigate(action)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
