package com.example.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.cinema.data.api.TestInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

@AndroidEntryPoint  //for depedency injection
class MainActivity : AppCompatActivity() {
  val base_url="http://41.89.227.23:8000/api/student/list/"
    lateinit var tv_data:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_data=findViewById(R.id.tv_data)

         getProgramData()
    }

    private fun getProgramData() {
        //retrofit builder object
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) // we need to add converter factory to  convert JSON object to Java object
            .baseUrl(base_url)
            .build()
            .create(TestInterface::class.java)

        //get data from retrofitbuilder obj

     val retrofitdata=retrofitBuilder.getPrograms()

        retrofitdata.enqueue(object : Callback<List<ProgramsItem>?> {
            override fun onResponse(
                call: Call<List<ProgramsItem>?>,
                response: Response<List<ProgramsItem>?>
            ) {
               val responsebody=response.body()!!
                val stringBuilder=StringBuilder()
                for (data in responsebody){
                    stringBuilder.append(data.Name)
                    stringBuilder.append("\n")
                }
                tv_data.text=stringBuilder
            }

            override fun onFailure(call: Call<List<ProgramsItem>?>, t: Throwable) {
                Log.d("MainActivity","onFailure"+t.message)
            }
        })

    }
}