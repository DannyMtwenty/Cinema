package com.example.cinema.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun LoadImage(view: ImageView,url : String?){
    //if url is not null
    url?.let {
        Glide.with(view).load(it).into(view)
    }


}