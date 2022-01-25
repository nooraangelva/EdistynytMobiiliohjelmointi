package com.example.edistynytmobiiliohjelmointi

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(private val context: Context, private val userId: Int) : ViewModelProvider.Factory {
    // Gets parameter to ViewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {

            return DetailViewModel(context, userId) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }


}
