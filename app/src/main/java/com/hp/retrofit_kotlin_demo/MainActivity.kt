package com.hp.retrofit_kotlin_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.hp.retrofit_kotlin_demo.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       //connect interface
        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        //get response in livedata with coroutine in livedatascope
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            emit(response)
        }

        //observe response
        responseLiveData.observe(this, Observer {
          val albumsList = it.body()?.listIterator()
          if(albumsList!=null){
              while (albumsList.hasNext()){
                val albumsItem = albumsList.next()
                val result =" "+"Album Title : ${albumsItem.title}"+"\n"+
                                  " "+"Album id : ${albumsItem.id}"+"\n"+
                                  " "+"User id : ${albumsItem.userId}"+"\n\n\n"
                textview.append(result)
                  println(result)
              }
          }
        })

    }














}
