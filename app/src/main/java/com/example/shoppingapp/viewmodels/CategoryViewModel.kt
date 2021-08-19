package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel@Inject constructor(
    val categoryRepository: CategoryRepository
) : ViewModel() {

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
        getCategoryBest()
    }

    fun getCategoryBest() = viewModelScope.launch {
        _categoryBest.value = categoryRepository.getBestAPI()
    }

    fun getCategoryClothes() = viewModelScope.launch {
        _categoryClothes.value = categoryRepository.getClothesAPI()
    }

    fun getCategoryOnepiece() = viewModelScope.launch {
        _categoryOnepiece.value = categoryRepository.getOnepieceAPI()
    }

    fun getCategoryPants() = viewModelScope.launch {
        _categoryPants.value = categoryRepository.getPantsAPI()
    }

    fun getCategorySkirt() = viewModelScope.launch {
        _categorySkirt.value = categoryRepository.getSkirtAPI()
    }

    fun getCategoryOuter() = viewModelScope.launch {
        _categoryOuter.value = categoryRepository.getOuterAPI()
    }

    fun getCategoryShoes() = viewModelScope.launch {
        _categoryShoes.value = categoryRepository.getShoesAPI()
    }

    fun getCategoryAccessories() = viewModelScope.launch {
        _categoryAccessories.value = categoryRepository.getAccessoriesAPI()
    }
}