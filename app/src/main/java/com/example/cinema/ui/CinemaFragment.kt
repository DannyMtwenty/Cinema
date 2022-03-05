package com.example.cinema.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinema.CinemaViewModel
import com.example.cinema.databinding.FragmentCinemaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CinemaFragment : Fragment() {
    lateinit var binding: FragmentCinemaBinding

    val cinemaAdapter = PaginationAdapter()

    val viewModel : CinemaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        binding= FragmentCinemaBinding.inflate(inflater,container,false)
        return binding.root
      //  return inflater.inflate(R.layout.fragment_cinema, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerView()

         cinemaAdapter.cinemaClick {
             findNavController().navigate(CinemaFragmentDirections.actionCinemaFragmentToDetailsFragment(it))
         }
        //observe list data as it changes
        viewModel.list.observe(viewLifecycleOwner){
            cinemaAdapter.submitData(lifecycle,it)  //submit to adapter
        }

        binding.cinemaSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
        })

    }

    private fun setRecyclerView() {
        binding.cinemaRecycler.apply {
            adapter=cinemaAdapter
            layoutManager=GridLayoutManager(requireContext(),2)  //2 columns

        }
    }

}