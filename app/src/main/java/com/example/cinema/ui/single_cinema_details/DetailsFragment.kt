package com.example.cinema.ui.single_cinema_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.cinema.CinemaViewModel
import com.example.cinema.R
import com.example.cinema.databinding.FragmentDetailsBinding
import com.example.cinema.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
   lateinit var binding : FragmentDetailsBinding
   val viewModel : CinemaViewModel by viewModels()
    val args : DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.backPress.setOnClickListener() {
            findNavController().popBackStack()
        }

        viewModel.getCinemaDetails(args.imdbId!!)
        //observe cinema details live data and return events also
        viewModel.cinemaDetails.observe(viewLifecycleOwner){
            when(it.getContentIfNotHandled()?.status){
               Status.LOADING ->{
                   binding.detailsProgress.visibility=View.VISIBLE
               }
                Status.ERROR ->{
                    binding.detailsProgress.visibility=View.GONE
                }
                Status.SUCCESS ->{
                    binding.detailsProgress.visibility=View.GONE

                    binding.cinemaItem=it.peekContent().data
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root


    }


}