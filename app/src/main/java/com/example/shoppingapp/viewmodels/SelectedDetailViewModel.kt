package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.BasketStuff
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.SelectedDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SelectedDetailViewModel @Inject constructor(
    val selectedDetailRepository: SelectedDetailRepository
): ViewModel() {
    private val _getColor : MutableLiveData<String> = MutableLiveData()

    private val _getSize : MutableLiveData<String> = MutableLiveData()

    private val _getStuffName : MutableLiveData<String> = MutableLiveData()

    private val _getPrice : MutableLiveData<Int> = MutableLiveData()
//
//    private val _goBasketList : MutableLiveData<MutableList<BasketStuff>> = MutableLiveData(mutableListOf())
//    val goBasketList : LiveData<MutableList<BasketStuff>> = _goBasketList

    private val _isComplete: MutableLiveData<Boolean> = MutableLiveData()
    val isComplete: LiveData<Boolean> = _isComplete


    fun getSelectedColor(value: String){
        _getColor.value = value
    }

    fun getSelectedSize(value : String){
        _getSize.value = value
    }

    fun getSelectedStuffName(value : String){
        _getStuffName.value = value
    }

    fun getSelectedPrice(value : Int){
        _getPrice.value = value
    }

    fun setIsComplete(value: Boolean){
        _isComplete.value = value
    }

    fun check(){
        if(!(_getColor.value!!.isEmpty() || _getSize.value!!.isEmpty())){
            val basket = BasketStuff("","",_getStuffName.value!!,_getPrice.value,_getSize.value!!,_getColor.value!!,true)
            insert(basket)
            _isComplete.value = true
        }else{
            _isComplete.value = false
        }
    }
    fun insert(basketStuff: BasketStuff)= viewModelScope.launch{
        withContext(Dispatchers.IO){
            selectedDetailRepository.insert(basketStuff)
        }
    }

    //BasketViewModel 에 넣기^^
//    fun toggleGoBasketItemList(isCheck: Boolean, basketStuff: BasketStuff){
//        val list = _goBasketList.value!!
//        if(isCheck){
//            list.add(basketStuff)
//            _goBasketList.value = list
//        }else{
//            list.remove(basketStuff)
//            _goBasketList.value = list
//        }
//    }
//    fun goBasketStuff(basketStuff: List<BasketStuff>)=viewModelScope.launch{
//        val list = mutableListOf<String>()
//        for (item in basketStuff){
//            list.add(item.uid)
//        }
//        withContext(Dispatchers.IO){
//            //selectedDetailRepository.goPayment(list)
//        }
//    }
}