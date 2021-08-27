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

    private val _getImage : MutableLiveData<String> = MutableLiveData()

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

    fun getSelectedImage(value : String){
        _getImage.value = value
    }

    fun setIsComplete(value: Boolean){
        _isComplete.value = value
    }

    fun check(){
        if(!(_getColor.value!!.isEmpty() || _getSize.value!!.isEmpty())){
            val basket = BasketStuff(null,_getImage.value!!,_getStuffName.value!!,_getPrice.value,_getSize.value!!,_getColor.value!!,false,false)
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

}