package com.example.cinema

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.cinema.data.api.CinemaDbInterface
import com.example.cinema.data.repository.CinemaDetailsRepository
import com.example.cinema.data.vo.cinemaDetails.CinemaItem
import com.example.cinema.data.vo.cinemaDetails.cinemaDetails
import com.example.cinema.ui.CinemaPagination
import com.example.cinema.utils.Events
import com.example.cinema.utils.Result
import com.example.cinema.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(private val cinemaDbInterface: CinemaDbInterface,private val cinemaDetailsRepository: CinemaDetailsRepository): ViewModel() {

    private val query =MutableLiveData<String>()

    val list=query.switchMap {   query->   //to switch the values within the live data
        Pager(PagingConfig(pageSize = 10)){    //api returns 10 items per page
            CinemaPagination(query,cinemaDbInterface)
        }.liveData.cachedIn(viewModelScope)
    }

fun setQuery(s: String){
    query.postValue(s)   //post value to live data
}

    private val _cinemaDetails =MutableLiveData<Events<Result<CinemaItem>>>()

    //livedata to be observed in fragment
   val cinemaDetails : LiveData<Events<Result<CinemaItem>>> =_cinemaDetails


    fun getCinemaDetails(imdbID : String) =viewModelScope.launch {

        _cinemaDetails.postValue(Events(Result(Status.LOADING,null,null)))

        _cinemaDetails.postValue(Events(cinemaDetailsRepository.getCinemaDetails(imdbID)))
    }




}