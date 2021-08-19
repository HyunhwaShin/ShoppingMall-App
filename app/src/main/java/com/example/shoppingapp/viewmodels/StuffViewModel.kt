package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.repositories.StuffRepository
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StuffViewModel @Inject constructor(
        val stuffRepository: StuffRepository
) : ViewModel() {

    private val _getStuff : MutableLiveData<List<Stuff>> = MutableLiveData()
    val getStuff : LiveData<List<Stuff>> = _getStuff

    private val _search: MutableLiveData<List<Stuff>> = MutableLiveData()
    val search: LiveData<List<Stuff>> = _search

    private val _likeItem :  MutableLiveData<Stuff> = MutableLiveData()
    val likeItem : LiveData<Stuff> = _likeItem

    private val _cancelItem : MutableLiveData<Stuff> = MutableLiveData()
    val cancelItem : LiveData<Stuff> = _cancelItem

    init {
        getAllStuff()
    }

    fun getAllStuff()= viewModelScope.launch{
        stuffRepository.getStuffAll().collect {
            _getStuff.value = it
        }
    }

    fun searchTest(text: String){
        if(text == ""){
            _search.value = _getStuff.value
        }else{
            val stuffList = _getStuff.value
            val result = mutableListOf<Stuff>()
            stuffList?.let { list ->
                for(item in list){
                    if(item.shop_name.contains(text)|| item.product_name.contains(text)){
                        result.add(item)
                    }
                }
                _search.value = result
            }
        }
    }
    fun updateLikeItem(stuff: Stuff)=viewModelScope.launch{
        withContext(Dispatchers.IO){
            stuffRepository.update(stuff)
        }
    }

    fun cancelLikeItem(stuff:Stuff) =viewModelScope.launch {
        withContext(Dispatchers.IO){
            stuffRepository.updateCancel(stuff)
        }
    }


    fun toggleLikeItemList(isCheck: Boolean, stuff: Stuff){
        if(isCheck){
            _likeItem.value = stuff
        }else {
            _cancelItem.value = stuff
        }
    }

}