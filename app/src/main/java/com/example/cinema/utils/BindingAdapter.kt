package com.example.cinema.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun LoadImage(view: ImageView,url : String){
    Glide.with(view).load(url).into(view)
}