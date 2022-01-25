package com.example.edistynytmobiiliohjelmointi

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    // Gets parameter to ViewModel

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(DataViewModel::class.java)) {

                return DataViewModel(context) as T

            }

            throw IllegalArgumentException("Unknown ViewModel class")

        }


}