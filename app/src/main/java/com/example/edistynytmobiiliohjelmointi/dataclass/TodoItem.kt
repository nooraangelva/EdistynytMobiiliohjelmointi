package com.example.edistynytmobiiliohjelmointi.dataclass

import com.google.gson.annotations.SerializedName

data class TodoItem (

    @SerializedName("userId") val userId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("completed") val completed : Boolean
)