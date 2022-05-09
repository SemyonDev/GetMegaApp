package com.getmega.getmegaapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getmega.domain.usecase.TrendingUseCase
import com.getmega.entities.Repo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeActivityViewModel(private val trendingUseCase: TrendingUseCase) : ViewModel(){

    fun getTrendingList(){
        val coroutineExceptionHandler = CoroutineExceptionHandler{ coroutineContext: CoroutineContext, throwable: Throwable ->
            Log.d("Tag", "Response = " + throwable.toString())
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val profileResponse = trendingUseCase.getTrendingDeveloperList()
            Log.d("Tag", "Response = " + profileResponse.toString())
        }

    }

    fun getList() = mutableListOf("asda","asd","Asd")

}