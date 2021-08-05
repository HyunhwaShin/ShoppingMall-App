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
    private val _categoryBest : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryBest : LiveData<List<Stuff>> = _categoryBest

    private val _categoryClothes : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryClothes : LiveData<List<Stuff>> = _categoryClothes

    private val _categoryOnepiece : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryOnepiece : LiveData<List<Stuff>> = _categoryOnepiece

    private val _categoryPants : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryPants : LiveData<List<Stuff>> = _categoryPants

    private val _categorySkirt : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categorySkirt : LiveData<List<Stuff>> = _categorySkirt

    private val _categoryOuter : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryOuter : LiveData<List<Stuff>> = _categoryOuter

    private val _categoryShoes : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryShoes : LiveData<List<Stuff>> = _categoryShoes

    private val _categoryAccessories : MutableLiveData<List<Stuff>> = MutableLiveData()
    val categoryAccessories : LiveData<List<Stuff>> = _categoryAccessories

    init {
        getStuffTest()
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

    //home
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
    //category
    fun getCategoryBest() = viewModelScope.launch {
        stuffRepository.getBest().collect {
            _categoryBest.value = it
        }
    }
    fun getCategoryClothes() = viewModelScope.launch {
        stuffRepository.getClothes().collect {
            _categoryClothes.value = it
        }
    }
    fun getCategoryOnepiece() = viewModelScope.launch {
        stuffRepository.getOnepiece().collect {
            _categoryOnepiece.value = it
        }
    }
    fun getCategoryPants() = viewModelScope.launch {
        stuffRepository.getPants().collect {
            _categoryPants.value = it
        }
    }

    fun getCategorySkirt() = viewModelScope.launch {
        stuffRepository.getSkirt().collect {
            _categorySkirt.value = it
        }
    }

    fun getCategoryOuter() = viewModelScope.launch {
        stuffRepository.getOuter().collect {
            _categoryOuter.value =it
        }
    }

    fun getCategoryShoes() = viewModelScope.launch {
        stuffRepository.getShoes().collect {
            _categoryShoes.value =it
        }
    }

    fun getCategoryAccessories() = viewModelScope.launch{
        stuffRepository.getAccessories().collect {
            _categoryAccessories.value = it
        }
    }
}