package com.example.shoppingapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.repositories.StuffRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel@Inject constructor(
) : ViewModel() {}