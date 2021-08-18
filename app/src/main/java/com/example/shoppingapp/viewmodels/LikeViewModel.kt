package com.example.shoppingapp.viewmodels

import androidx.lifecycle.*
import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.repositories.LikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
        val likeRepository: LikeRepository
) : ViewModel() {

    private val _getLikeStuff : MutableLiveData<List<Stuff>> = MutableLiveData()
    val getLikeStuff : LiveData<List<Stuff>> = _getLikeStuff

    private val _isDelete : MutableLiveData<Boolean> = MutableLiveData()
    val isDelete : LiveData<Boolean> = _isDelete

    private val _deleteItemList: MutableLiveData<MutableList<Stuff>> = MutableLiveData(mutableListOf())
    val deleteItemList: LiveData<MutableList<Stuff>> = _deleteItemList

    init {
        getLikeStuff()
    }

    fun getLikeStuff() = viewModelScope.launch {
        likeRepository.getLikeStuffs().collect {
            _getLikeStuff.value = it
        }
    }

    fun deleteStuff(stuff: List<Stuff>){
        val list = _getLikeStuff.value!!.toMutableList()
        list.removeAll(stuff)
        _getLikeStuff.value = list
        _isDelete.value = true

        likeRepository.delete(stuff)
    }

    fun toggleDeleteItemList(isCheck: Boolean, stuff: Stuff){
        val list = _deleteItemList.value!!
        if(isCheck){
            list.add(stuff)
            _deleteItemList.value = list
        }else{
            list.remove(stuff)
            _deleteItemList.value = list
        }
    }
}