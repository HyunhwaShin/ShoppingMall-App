package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.db.User
import com.example.shoppingapp.other.EventWrapper
import com.example.shoppingapp.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel@Inject constructor(
    val userRepository: UserRepository
) : ViewModel(){
    private val _setName : MutableLiveData<String> = MutableLiveData()

    private val _setEmail : MutableLiveData<String> = MutableLiveData()

    private val _isComplete = MutableLiveData<EventWrapper<Boolean>>()
    val isComplete: LiveData<EventWrapper<Boolean>>
        get() = _isComplete


    fun setUserName(value : String){
        _setName.value = value
    }

    fun setUserEmail(value: String){
        _setEmail.value = value
    }

    fun setIsComplete(value : Boolean) {
        _isComplete.value = EventWrapper(value)
    }

    fun insertNameAndEmail(user : User) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            userRepository.userDao.insert(user)
        }
    }

    fun insertCheck(){
        if(!(_setName.value!!.isEmpty() || _setEmail.value!!.isEmpty())){
            val user = User(_setEmail.value!!,_setName.value!!)
            insertNameAndEmail(user)
            setIsComplete(true)
        }else{
            setIsComplete(false)
        }
    }

}