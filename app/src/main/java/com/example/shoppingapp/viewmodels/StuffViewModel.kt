package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.repositories.StuffRepository
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StuffViewModel @Inject constructor(
        val stuffRepository: StuffRepository
) : ViewModel() {

    val getStuff : LiveData<List<Stuff>> = stuffRepository.getStuffs().asLiveData()
    //dummy
    private val _mockStuff : MutableLiveData<List<Stuff>> = MutableLiveData()
    val mockStuff: LiveData<List<Stuff>> = _mockStuff

    private val _mockSearch: MutableLiveData<List<Stuff>> = MutableLiveData()
    val mockSearch: LiveData<List<Stuff>> = _mockSearch

    //category
    val categoryBest : LiveData<List<Stuff>> = stuffRepository.getBest().asLiveData()
    val categoryClothes : LiveData<List<Stuff>> = stuffRepository.getClothes().asLiveData()
    val categoryOnepiece : LiveData<List<Stuff>> = stuffRepository.getOnepiece().asLiveData()
    val categoryPants : LiveData<List<Stuff>> = stuffRepository.getPants().asLiveData()
    val categorySkirt : LiveData<List<Stuff>> = stuffRepository.getSkirt().asLiveData()
    val categoryOuter : LiveData<List<Stuff>> = stuffRepository.getOuter().asLiveData()
    val categoryShoes : LiveData<List<Stuff>> = stuffRepository.getShoes().asLiveData()
    val _categoryAccessories : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryAccessories : LiveData<List<Stuff>> = _categoryAccessories

    init {
        getStuffTest()
    }

    fun getCategoryAccessories() = viewModelScope.launch{
        stuffRepository.getAccessories().collect {
            _categoryAccessories.value = it
        }
    }

    fun getStuffTest()= viewModelScope.launch {
        delay(1000)
        val stuffList = mutableListOf<Stuff>()
        stuffList.add(Stuff(1,"현화가게","분홍색 바지","pants",5000,"M","pink","link",false,false))
        stuffList.add(Stuff(2,"화가게","갈색셔츠","clothes",40000,"L","brown","link",false,false))
        stuffList.add(Stuff(3,"민정가게","레이스치마","skirt",200,"M","white","link",false,false))
        stuffList.add(Stuff(4,"현","진주목걸이","accessories",90010,"S","yellow","link",false,false))
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