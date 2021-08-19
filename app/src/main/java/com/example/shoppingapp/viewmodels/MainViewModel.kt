package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.StuffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val stuffRepository: StuffRepository
): ViewModel() {

    fun init(){
        getAllStuff()
    }

    fun getAllStuff()=viewModelScope.launch{
        withContext(Dispatchers.IO){
            stuffRepository.insert(stuffRepository.getStuffAPI())
        }
    }

}