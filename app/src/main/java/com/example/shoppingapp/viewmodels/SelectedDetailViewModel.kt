package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.repositories.SelectedDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedDetailViewModel @Inject constructor(
    val selectedDetailRepository: SelectedDetailRepository
): ViewModel() {
    val _getColor : MutableLiveData<String> = MutableLiveData()
//    val getColor : LiveData<String> = _getColor

    val _getSize : MutableLiveData<String> = MutableLiveData()
//    val getSize : LiveData<String> = _getSize

    private val _goBasketList : MutableLiveData<MutableList<BasketStuff>> = MutableLiveData(mutableListOf())
    val goBasketList : LiveData<MutableList<BasketStuff>> = _goBasketList

    fun getSelectedColor(){

    }
    fun getSelectedSize(){

    }
    fun toggleGoBasketItemList(isCheck: Boolean, basketStuff: BasketStuff){
        val list = _goBasketList.value!!
        if(isCheck){
            list.add(basketStuff)
            _goBasketList.value = list
        }else{
            list.remove(basketStuff)
            _goBasketList.value = list
        }
    }
}