package com.example.cinema.ui

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.BR
import com.example.cinema.data.vo.Cinema

import com.example.cinema.databinding.CinemaItemViewholderBinding
import com.example.cinema.databinding.CinemaItemViewholderBindingImpl

//presents PagingData(container for paginatedData) in a RecyclerView
class PaginationAdapter : PagingDataAdapter<Cinema,PaginationAdapter.MyViewHolder>(diff_Util) {

    var onClick: ((String) -> Unit)? = null



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
    fun cinemaClick(listener: (String)->Unit){
        onClick=listener
    }

    inner class MyViewHolder(val viewHolderBinding: CinemaItemViewholderBinding) :RecyclerView.ViewHolder(viewHolderBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data_cinema=getItem(position)
        holder.viewHolderBinding.setVariable(BR.cinema,data_cinema)

        holder.viewHolderBinding.root.setOnClickListener(){
            onClick?.let {
                it(data_cinema?.imdbID!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CinemaItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}