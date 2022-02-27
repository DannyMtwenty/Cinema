package com.example.cinema.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinema.data.api.CinemaDbInterface
import com.example.cinema.data.vo.Cinema
import com.example.cinema.utils.Constants
import android.util.Log

class CinemaPagination(val s :  String,val cinemaDbInterface: CinemaDbInterface) :
    PagingSource<Int, Cinema>() {   //PagingSource -> where to retrieve the items

    //for subsequent refresh calls to PagingSource.load()
    override fun getRefreshKey(state: PagingState<Int, Cinema>): Int? {

        //if anchorposition is not null
        return state.anchorPosition?.let {
        var anchorpage=state.closestPageToPosition(it)  //get closest pg position
          anchorpage?.prevKey?.plus(1)?:anchorpage?.nextKey?.minus(1)

      }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cinema> {

        val page=params.key?:1  //return pg1 if null

        //get cinema data
        return try {

            val data =cinemaDbInterface.getCinema(s, page,Constants.api_key)
            Log.d("TAG", "load: ${data.body()}")
            LoadResult.Page(
                data = data.body()?.Search!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.Search?.isEmpty()!!) null else page + 1
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}