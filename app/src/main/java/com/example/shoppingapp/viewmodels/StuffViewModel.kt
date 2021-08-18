package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.repositories.StuffRepository
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StuffViewModel @Inject constructor(
        val stuffRepository: StuffRepository
) : ViewModel() {

    private val _getStuffAPI : MutableLiveData<List<Stuff>> = MutableLiveData()
    var getStuffAPI : LiveData<List<Stuff>> = _getStuffAPI

    private val _search: MutableLiveData<List<Stuff>> = MutableLiveData()
    val search: LiveData<List<Stuff>> = _search

    private val _likeItemList :  MutableLiveData<MutableList<Stuff>> = MutableLiveData(mutableListOf())
    val likeItemList : LiveData<MutableList<Stuff>> = _likeItemList


    init {
        getAllStuff()
    }

    fun getAllStuff()=viewModelScope.launch{
        _getStuffAPI.value = stuffRepository.getStuffAPI()
    }

    fun searchTest(text: String){
        if(text == ""){
            _search.value = _getStuffAPI.value
        }else{
            val stuffList = _getStuffAPI.value
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

    fun toggleLikeItemList(isCheck: Boolean, stuff: Stuff){
        val list = _likeItemList.value!!
        if(isCheck){
            list.add(stuff)
            _likeItemList.value = list
        }
    }

}