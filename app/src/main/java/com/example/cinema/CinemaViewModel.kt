package com.example.cinema

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.cinema.data.api.CinemaDbInterface
import com.example.cinema.ui.CinemaPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(private val cinemaDbInterface: CinemaDbInterface): ViewModel() {

    private val query =MutableLiveData<String>()

    val list=query.switchMap {   query->   //to switch the values within the live data
        Pager(PagingConfig(pageSize = 10)){    //api returns 10 items per page
            CinemaPagination(query,cinemaDbInterface)
        }.liveData.cachedIn(viewModelScope)
    }

fun setQuery(s: String){
    query.postValue(s)   //post value to live data
}

}