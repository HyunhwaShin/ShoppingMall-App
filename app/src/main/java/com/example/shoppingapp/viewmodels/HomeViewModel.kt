package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.repositories.StuffRepository
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        val stuffRepository: StuffRepository
) : ViewModel() {

    val getStuff : LiveData<List<Stuff>> = stuffRepository.getStuffs().asLiveData()
    //dummy
    private val _mockStuff : MutableLiveData<List<Stuff>> = MutableLiveData()
    val mockStuff: LiveData<List<Stuff>> = _mockStuff

    private val _mockSearch: MutableLiveData<List<Stuff>> = MutableLiveData()
    val mockSearch: LiveData<List<Stuff>> = _mockSearch

    init {
        getStuffTest()
    }

    fun getStuffTest()= viewModelScope.launch {
        delay(1000)
        val stuffList = mutableListOf<Stuff>()
        stuffList.add(Stuff(1,"현화가게","진주목걸이","50.000",false,false))
        stuffList.add(Stuff(2,"화가게","목걸이","5.400",false,false))
        stuffList.add(Stuff(3,"민정가게","귀걸이","10.000",false,false))
        stuffList.add(Stuff(4,"현","팔찌","30.000",false,false))
        _mockStuff.value = stuffList
    }

    fun searchTest(text: String){
        if(text == ""){
            _mockSearch.value = _mockStuff.value
        }else{
            val stuffList = _mockStuff.value
            val result = mutableListOf<Stuff>()
            stuffList?.let { list ->
                for(item in list){
                    if(item.shopName.contains(text)){
                        result.add(item)
                    }
                }
                _mockSearch.value = result
            }
        }
    }
}