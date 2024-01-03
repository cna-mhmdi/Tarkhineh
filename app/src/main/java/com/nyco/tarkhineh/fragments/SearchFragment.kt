package com.nyco.tarkhineh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.adapters.SearchAdapter
import com.nyco.tarkhineh.databinding.FragmentSearchBinding
import com.nyco.tarkhineh.model.SearchFood

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerSearch : RecyclerView

    private val searchAdapter by lazy {
        SearchAdapter(object : SearchAdapter.SearchFoodsClickListener{
            override fun onSearchFoodsClick(searchFood: SearchFood) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).setSupportActionBar(binding.searchToolbar)
        (activity as AppCompatActivity).supportActionBar?.title = null

        recyclerSearch = binding.recyclerSearch
        recyclerSearch.adapter = searchAdapter
//        searchAdapter.addSearchFood()




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}