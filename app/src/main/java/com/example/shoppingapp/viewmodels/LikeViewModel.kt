package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.LikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
        val likeRepository: LikeRepository
) : ViewModel() {

    val getStuff : LiveData<List<Stuff>> = likeRepository.getStuffs().asLiveData()
    //dummy
    private val _mockStuff : MutableLiveData<List<Stuff>> = MutableLiveData()
    val mockStuff: LiveData<List<Stuff>> = _mockStuff

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
}