package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.DetailStuffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailStuffViewModel @Inject constructor(
    val detailStuffRepository: DetailStuffRepository
) : ViewModel() {

    private val _likeItem : MutableLiveData<Stuff> = MutableLiveData()
    val likeItem : LiveData<Stuff> = _likeItem

    private val _cancelItem : MutableLiveData<Stuff> = MutableLiveData()
    val cancelItem : LiveData<Stuff> = _cancelItem


    fun likeItem(stuff: Stuff) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            detailStuffRepository.update(stuff)
        }
    }

    fun toggleLikeItem(isCheck : Boolean, stuff : Stuff) {
        if(isCheck){
            _likeItem.value = stuff
        }else{
            _cancelItem.value = stuff
        }
    }

    fun cancelLikeItem(stuff: Stuff) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            detailStuffRepository.updateCancel(stuff)
        }
    }
}