package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.repositories.StuffRepository
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StuffViewModel @Inject constructor(
        val stuffRepository: StuffRepository
) : ViewModel() {

    //home
    private val _getStuffAPI : MutableLiveData<List<Stuff>> = MutableLiveData()
    var getStuffAPI : LiveData<List<Stuff>> = _getStuffAPI

    //search
    private val _search: MutableLiveData<List<Stuff>> = MutableLiveData()
    val search: LiveData<List<Stuff>> = _search

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
        getAllStuff()
    }

    fun getAllStuff()=viewModelScope.launch{
        _getStuffAPI.value = stuffRepository.getStuffAPI()
    }

    //home
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