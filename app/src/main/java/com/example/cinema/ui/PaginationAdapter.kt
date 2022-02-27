package com.example.cinema.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.BR
import com.example.cinema.data.vo.Cinema

import com.example.cinema.databinding.CinemaItemViewholderBinding
import com.example.cinema.databinding.CinemaItemViewholderBindingImpl

//presents PagingData(container for paginatedData) in a RecyclerView
class PaginationAdapter : PagingDataAdapter<Cinema,PaginationAdapter.MyViewHolder>(diff_Util) {

    companion object{
        val diff_Util= object  : DiffUtil.ItemCallback<Cinema>(){
            override fun areItemsTheSame(oldItem: Cinema, newItem: Cinema): Boolean {
                return oldItem.imdbID==oldItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Cinema, newItem: Cinema): Boolean {
                return  oldItem == newItem
            }
        }
    }


    inner class MyViewHolder(val viewHolderBinding: CinemaItemViewholderBinding) :RecyclerView.ViewHolder(viewHolderBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolderBinding.setVariable(BR.cinema,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CinemaItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}