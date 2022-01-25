package com.example.edistynytmobiiliohjelmointi

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.edistynytmobiiliohjelmointi.dataclass.TodoItem
import com.example.edistynytmobiiliohjelmointi.dataclass.UserItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class DetailViewModel(val context: Context, private val userId: Int) : ViewModel() {

    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    private val _completed = MutableLiveData<String>()
    val completed: LiveData<String>
        get() = _completed

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    init {
        getData()
        getUser()
    }

    private fun getData(){


        // this is the url where we want to get our data from
        val todoDetailUrl = "https://jsonplaceholder.typicode.com/todos/$userId"

        // Request a string response from the provided URL.
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.GET, todoDetailUrl,
            Response.Listener { response ->

                // print the response as a whole
                // we can use GSON to modify this response into something more usable
                Log.d("GET-Response", response)
                val item : TodoItem = gson.fromJson(response, TodoItem::class.java)

                _completed.value = "Completed: "+item.completed.toString()
                _id.value = "Id: "+item.id
               _title.value = "Title: "+item.title


            },
            Response.ErrorListener {
                // typically this is a connection error
                Log.d("GET-Error", it.toString())
            })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {

                // basic headers for the data
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }
        }

        // Add the request to the RequestQueue. This has to be done in both getting and sending new data.
        // if using this in an activity, use "this" instead of "context"
        Singleton.getInstance(context).addToRequestQueue(stringRequest)
    }

    private fun getUser(){


        // this is the url where we want to get our data from
        val userNameUrl = "https://jsonplaceholder.typicode.com/user/$userId"

        // Request a string response from the provided URL.
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.GET, userNameUrl,
            Response.Listener { response ->

                // print the response as a whole
                // we can use GSON to modify this response into something more usable
                Log.d("GET-Response", response)
                val item : UserItem = gson.fromJson(response, UserItem::class.java)

                _userName.value = "User: "+item.username


            },
            Response.ErrorListener {
                // typically this is a connection error
                Log.d("GET-Error", it.toString())
            })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {

                // basic headers for the data
                val headers = HashMap<String, String>()
                headers["Accept"] = "application/json"
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }
        }

        // Add the request to the RequestQueue. This has to be done in both getting and sending new data.
        // if using this in an activity, use "this" instead of "context"
        Singleton.getInstance(context).addToRequestQueue(stringRequest)
    }
}