package com.example.shoppingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.shoppingapp.repositories.StuffRepository
import com.example.shoppingapp.db.Stuff
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        val stuffRepository: StuffRepository
) : ViewModel() {

    val getStuff : LiveData<List<Stuff>> = stuffRepository.getStuffs().asLiveData()
}

